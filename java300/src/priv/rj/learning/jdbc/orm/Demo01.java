package priv.rj.learning.jdbc.orm;

import priv.rj.learning.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 将表中的一条记录封装到Object数组中
 *
 * 使用List<Object[]> 封装多条记录
 */
public class Demo01 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object[]> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();
            //数组封装一条记录
            while (rs.next()){
                Object[] objs = new Object[3];
                //System.out.println(rs.getString(1)+"----"+rs.getDouble(2)+"---"+rs.getInt(3));
                objs[0] = rs.getString(1);
                objs[1] = rs.getString(2);
                objs[2] = rs.getString(3);

                list.add(objs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,ps,conn);
        }
        for (Object[] objs : list){
        System.out.println("" + objs[0]+objs[1]+objs[2]);
        }
    }
}
