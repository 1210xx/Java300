package priv.rj.learning.jdbc;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 测试时间
 * 查找指定日期范围的数据
 */
public class JDBCFindDate {

    /**
     * 将字符串代表的时间转为long数组（格式yyyy-MM-dd hh:mm:ss）
     * @param dateStr
     * @return
     */
    public static long str2Date(String dateStr){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return format.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

//            ps = connection.prepareStatement("select * from t_user where regTime > ? and regTime < ?");
//
//            java.sql.Date start = new java.sql.Date(str2Date("2020-05-20 10:23:45"));
//            java.sql.Date end = new java.sql.Date(str2Date("2020-05-23 10:23:45"));
//
//            ps.setObject(1, start);
//            ps.setObject(2, end);
            ps = connection.prepareStatement("select * from t_user where lastLoginTime > ? and lastLoginTime < ? order by lastLoginTime");
            Timestamp start = new Timestamp(str2Date("2020-05-20 10:10:20"));
            Timestamp end = new Timestamp(str2Date("2020-05-20 19:09:20"));
            ps.setObject(1, start);
            ps.setObject(2, end);
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getInt("id") + "----" + rs.getString("username") + "----" + rs.getTimestamp("lastLoginTime"));
            }

        }catch (ClassNotFoundException | SQLException e ){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                if (null != ps){
                    ps.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if (null != connection){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
