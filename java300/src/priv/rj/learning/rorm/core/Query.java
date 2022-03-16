package priv.rj.learning.rorm.core;


import priv.rj.learning.rorm.bean.ColumnInfo;
import priv.rj.learning.rorm.bean.TableInfo;
import priv.rj.learning.rorm.utils.JDBCUtils;
import priv.rj.learning.rorm.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责查询（对外提供服务的核心类）
 *
 * @author rjjerry
 */
public abstract class Query implements Cloneable{

    /**
     * 采用模版方法模式将JDBC操作封装成模版，便于重用
     * @param sql sql语句
     * @param params sql参数
     * @param clazz 记录要封装到的bean
     * @param back CallBack 实现类，实现回调
     * @return 返回结果
     */
    public Object executeQueryTemplate(String sql, Object[] params, Class clazz, CallBack back) {

        Connection conn = DBManger.getConn();
        //存放查询结果的list
        List list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);

            JDBCUtils.handleParams(ps, params);
            System.out.println(ps);
            rs = ps.executeQuery();
            return back.doExecute(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBManger.close(ps, conn);
        }
    }


    /**
     * 直接执行一个DML语句
     *
     * @param sql    sql语句
     * @param params 参数
     * @return 执行sql后影响记录的行数
     */
    public int executeDML(String sql, Object[] params) {
        Connection conn = DBManger.getConn();
        int count = 0;
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            //ps传参
//            if (null != params){
//                for (int i = 0; i < params.length; i++) {
//                    ps.setObject(1 + i, params[i]);
//                }
//            }
            JDBCUtils.handleParams(ps, params);
            System.out.println(ps);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManger.close(ps, conn);

            return count;
        }
    }

    /**
     * 将一个对象存储到数据库中
     * 把对象中不为null的属性存储到数据库中，如果数字为null，则存放0。
     *
     * @param obj 参数
     */
    public void insert(Object obj) {
        //obj --> 表中 insert into 表名 (id, username, pwd) values(?,?,?)
        Class c = obj.getClass();
        //存储sql参数对象
        List<Object> params = new ArrayList<>();

        TableInfo tableInfo = TableContext.poClassTableMap.get(c);

        StringBuilder sql = new StringBuilder("insert into " + tableInfo.getTname() + " (");

        Field[] fs = c.getDeclaredFields();
        //计算不为0的field个数
        int countNotNullField = 0;

        for (Field f : fs) {
            String fieldName = f.getName();
            Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);
            if (fieldValue != null) {
                countNotNullField++;
                sql.append(fieldName + ",");
                params.add(fieldValue);
            }
        }

        sql.setCharAt(sql.length() - 1, ')');

        sql.append(" values(");

        for (int i = 0; i < countNotNullField; i++) {
            sql.append("?,");
        }

        sql.setCharAt(sql.length() - 1, ')');

        executeDML(sql.toString(), params.toArray());
    }

    /**
     * 删除clazz表示类对应的表中的记录（指定主键id）
     *
     * @param clazz 与表对应的类的Class对象
     * @param id    主键的值
     */
    public void delete(Class clazz, Object id) {
        //delete from User where id = 2
        //Emp.class,2 --> delete from emp where id = 2
        //通过Class对象找到TableInfo  User --> user, User
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);

        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();

        String sql = "delete from " + tableInfo.getTname() + " where " + onlyPriKey.getName() + " = ?;";

        executeDML(sql, new Object[]{id});
    }


    /**
     * 删除对象在数据库中的记录（对象所在的类对应到表，对象的主键的值对应到记录）
     *
     * @param obj 目标对象
     */
    public void delete(Object obj) {
        Class c = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(c);
        //主键
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();

        //通过反射机制，调用属性对应的get或set方法
//        try {
//            Method method = c.getMethod("get" + StringUtils.firstChar2UpperCase(onlyPriKey.getName()), null);
//            Object priKeyValue = method.invoke(obj, null);
//            delete(c, priKeyValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Object priKeyValue = ReflectUtils.invokeGet(onlyPriKey.getName(), obj);
        delete(c, priKeyValue);

    }

    /**
     * 更新对象对应对应的记录，有指定的字段
     *
     * @param obj        所更新的对象
     * @param fieldNames 要更新的属性列表
     * @return 执行sql后影响的记录条数
     */
    public int update(Object obj, String[] fieldNames) {
        //update user set uname=?,pwd=?
        //obj{"uname","pwd"} ---> update 表名 set uname = ?, pwd = ? where id =?
        Class c = obj.getClass();
        List<Object> params = new ArrayList<>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(c);

        ColumnInfo priKey = tableInfo.getOnlyPriKey();

        StringBuilder sql = new StringBuilder("update " + tableInfo.getTname() + " set ");


        for (String fname : fieldNames) {
            Object fvalue = ReflectUtils.invokeGet(fname, obj);
            params.add(fvalue);
            sql.append(fname + "=?,");
        }

        sql.setCharAt(sql.length() - 1, ' ');

        sql.append("where " + priKey.getName() + "=? ");

        params.add(ReflectUtils.invokeGet(priKey.getName(), obj));
        return executeDML(sql.toString(), params.toArray());
    }


    /**
     * 查询返回多行记录，并将记录封装到clazz指定的类的对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的javabean类的Class对象
     * @param params sql参数
     * @return 查询到的结果
     */
    public List queryRows(final String sql, final Class clazz, final Object[] params) {

        return (List) executeQueryTemplate(sql, params, clazz, new CallBack() {
            @Override
            public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs) {
                List list = null;
                try {
                    ResultSetMetaData metaData = rs.getMetaData();
                    //多行
                    while (rs.next()) {
                        if (null == list){
                            list = new ArrayList();
                        }
                        //调用javabean的无参构造器
                        Object rowObj = clazz.newInstance();

                        //多列 select uername,pwd,age from user where id = ? and age >18
                        for (int i = 0; i < metaData.getColumnCount(); i++) {
                            //username
                            String columnName = metaData.getColumnLabel(i + 1);

                            Object columnValue = rs.getObject(i + 1);

                            //对用rowObj的setUsername方法，将columnValue值放进去
                            ReflectUtils.invokeSet(rowObj, columnName, columnValue);

                        }

                        list.add(rowObj);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return list;
            }
        });
    }

    /**
     * 查询返回一行记录，并将记录封装到clazz指定的类的对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的javabean类的Class对象
     * @param params sql参数
     * @return 查询到的结果
     */
    public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    /**
     * 查询返回一个值（一行一列），并将该值返回
     *
     * @param sql    查询语句
     * @param params sql参数
     * @return 查询到的结果
     */
    public Object queryValue(String sql, Object[] params) {

        return executeQueryTemplate(sql, params, null, new CallBack() {
            @Override
            public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs) {
                Object value = null;

                    try {
                        while (rs.next()) {
                            value = rs.getObject(1);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                return value;
            }
        });

    }

    /**
     * 查询返回一个数字，并将该值返回
     *
     * @param sql    查询语句
     * @param params sql参数
     * @return 查询到的结果
     */
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }

    /**
     * 根据主键返回对应的对象
     * @param clazz 对象
     * @param id 主键
     * @return 返回对象
     */
    public Object queryById(Class clazz, Object id){
        //select * from emp where id = ?
        //通过Class对象找到TableInfo  User --> user, User
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);

        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();

        String sql = "select * from " + tableInfo.getTname() + " where " + onlyPriKey.getName() + " = ?;";

        return queryUniqueRow(sql, clazz, new Object[]{id});
    }

    /**
     * 分页查询
     *
     * @param pageNum 第几页数据
     * @param size    每页显示多少数据
     * @return 查询结果
     */
    public abstract Object queryPagenate(int pageNum, int size);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

