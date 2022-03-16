package priv.rj.learning.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * Date类型
 * 数据库date类型
 */
public class JDBCDateType {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

            for (int i = 0; i < 1000; i++) {
                //
                ps1 = connection.prepareStatement("insert into t_user(username,pwd,regTime,lastLoginTime) values(?,?,?,?)");

                ps1.setObject(1, "random" + i);
                ps1.setObject(2, 111222);

                int rand = 100000000 + new Random().nextInt(1000000000);

                java.sql.Date date = new java.sql.Date(System.currentTimeMillis()-rand);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis()-rand);
                ps1.setDate(3, date);
                // Timestamp timestamp = new Timestamp(2015, 10, 15, 8,25, 20,22);
                //可以使用Calendar类或者DateFormat
                ps1.setTimestamp(4,timestamp);
                ps1.execute();

            }


            System.out.println("插入一个用户.....rjrj");

        }catch (ClassNotFoundException | SQLException e ){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                if (null != ps1){
                    ps1.close();
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
