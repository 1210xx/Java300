package priv.rj.learning.jdbc.orm;

import priv.rj.learning.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将表中的一条记录封装到Map中
 *
 * 使用List<Object[]> 封装多条记录
 */
public class Demo02 {
    /**
     * 使用一个map存放一条记录
     */
    public static void test01(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //使用一个map封装一条记录
        Map<String,Object> row = new HashMap<>();
        try {
            conn = JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();
            //数组封装一条记录
            while (rs.next()){
                //System.out.println(rs.getString(1)+"----"+rs.getDouble(2)+"---"+rs.getInt(3));
                row.put("emname", rs.getObject(1));
                row.put("salary", rs.getObject(2));
                row.put("age", rs.getObject(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,ps,conn);
        }
        for (String key : row.keySet()){
            System.out.print(key +"----"+ row.get(key) +"\t");
        }
        System.out.println(row);
    }

    /**
     * list封装多条记录多个map
     */
    public static void test02(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //使用一个map封装一条记录
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();
            //数组封装一条记录
            while (rs.next()){
                //System.out.println(rs.getString(1)+"----"+rs.getDouble(2)+"---"+rs.getInt(3));
                Map<String,Object> row = new HashMap<>();
                row.put("emname", rs.getObject(1));
                row.put("salary", rs.getObject(2));
                row.put("age", rs.getObject(3));

                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,ps,conn);
        }
        for (Map<String,Object> row : list) {
            for (String key : row.keySet()) {
                System.out.print(key + "----" + row.get(key) + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 用map封装多个map（一条记录）
     */
    public static void test03(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //使用一个map封装一条记录
        Map<String,Map<String,Object>> maps = new HashMap<>();
        try {
            conn = JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();
            //数组封装一条记录
            while (rs.next()){
                //System.out.println(rs.getString(1)+"----"+rs.getDouble(2)+"---"+rs.getInt(3));
                Map<String,Object> row = new HashMap<>();
                row.put("emname", rs.getObject(1));
                row.put("salary", rs.getObject(2));
                row.put("age", rs.getObject(3));

                maps.put(rs.getString(1), row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,ps,conn);
        }
        for (String empname : maps.keySet() ) {
            Map<String,Object> row = maps.get(empname);
            for (String key : row.keySet()) {
                System.out.print(key + "----" + row.get(key) + "\t");
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
        test03();
    }
}

