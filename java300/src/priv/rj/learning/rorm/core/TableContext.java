package priv.rj.learning.rorm.core;

import priv.rj.learning.rorm.bean.ColumnInfo;
import priv.rj.learning.rorm.bean.TableInfo;
import priv.rj.learning.rorm.utils.JavaFileUtils;
import priv.rj.learning.rorm.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责获取管理数据库的所有表结构和类结构的关系，可以根据表结构生成类结构
 * @author rjjerry
 */
public class TableContext {
    /**
     * 表名为key，表信息对象为value
     */
    public static Map<String, TableInfo> tables = new HashMap<>();
    /**
     * 将po的Class对象和表信息对应起来，便于重用；
     */
    public static Map<Class,TableInfo> poClassTableMap = new HashMap<>();

    private TableContext(){}

    static{
        try {
            //初始化获得表的信息
            Connection conn = DBManger.getConn();
            DatabaseMetaData dbmd = conn.getMetaData();

            ResultSet tableSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tableSet.next()){

                String tableName = (String)tableSet.getObject("TABLE_NAME");

                TableInfo ti = new TableInfo(tableName, new ArrayList<ColumnInfo>(),new HashMap<String,ColumnInfo>());

                tables.put(tableName, ti);

                ResultSet set = dbmd.getColumns(null, "%", tableName, "%");

                while (set.next()){
                    ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0);
                    ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
                }

                //查询t_user中的主键
                ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);
                while (set2.next()){
                    ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
                    //设为主键类型
                    ci2.setKeyType(1);
                    ti.getPriKeys().add(ci2);
                }
                if (ti.getPriKeys().size() > 0){
                    //取唯一主键，方便使用。如果是联合主键，则为空
                    ti.setOnlyPriKey(ti.getPriKeys().get(0));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //更新类结构
        updateJavaPOFile();
        //加载po包下面的类，便于重用
        loadPOTables();
    }

    /**
     * 根据表结构，更新配置po下面的java类
     * 实现了从表结构到类结构
     */
    public static void updateJavaPOFile() {
        Map<String, TableInfo> map = TableContext.tables;
        for (TableInfo t : map.values()) {
            JavaFileUtils.createJavaPOFile(t, new MysqlTypeConvertor());
        }
    }

    /**
     * 加载po包下的类
     */
    public static void loadPOTables(){

            for (TableInfo tableInfo : tables.values()) {
                try {
                    Class c = Class.forName(DBManger.getConf().getPoPackage() + "." + StringUtils.firstChar2UpperCase(tableInfo.getTname()));
                    poClassTableMap.put(c, tableInfo);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
//       Class clazz = Class.forName("priv.rj.learning.rorm.po.Emp");
//       poClassTableMap.put(clazz, TableInfo);
    }

    public static void main(String[] args) {
        Map<String,TableInfo> tables  = TableContext.tables;
        System.out.println(tables);
        for (TableInfo tableInfo : poClassTableMap.values()){
            System.out.println(tableInfo.getOnlyPriKey().getName());
        }


    }
}
