package priv.rj.learning.jdbc.orm;

import priv.rj.learning.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 将表中一条的数据存储到javabean对象中
 * 使用List<javabean>封装多条对象
 */
public class Demo03 {

    /**
     * 使用一个对象存放一条记录
     */
    public static void test01(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //使用一个对象封装一条记录
        Emp emp = null;

        try {
            conn = JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();
            //数组封装一条记录
            while (rs.next()){
                //System.out.println(rs.getString(1)+"----"+rs.getDouble(2)+"---"+rs.getInt(3));
                emp = new Emp(rs.getString(1), rs.getDouble(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,ps,conn);
        }

        System.out.println(emp.getEmpname()+"----"+ emp.getSalary() +"----" + emp.getAge());
    }

    /**
     * list封装多条记录多个对象
     */
    public static void test02(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //使用一个map封装一条记录
        List<Emp> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();
            //数组封装一条记录
            while (rs.next()){
                //System.out.println(rs.getString(1)+"----"+rs.getDouble(2)+"---"+rs.getInt(3));
                Emp emp = new Emp(rs.getString(1), rs.getDouble(2), rs.getInt(3));

                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,ps,conn);
        }

        for (Emp emp : list){
            System.out.println(emp.getEmpname()+"----"+ emp.getSalary() +"----" + emp.getAge());
        }

    }

    public static void main(String[] args) {
        test02();
    }

}
