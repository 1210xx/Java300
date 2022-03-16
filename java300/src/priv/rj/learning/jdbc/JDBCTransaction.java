package priv.rj.learning.jdbc;



import java.sql.*;

/**
 * 测试事务
 */
public class JDBCTransaction {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

            //jdbc中默认为自动提交事务
            connection.setAutoCommit(false);

            //
            ps1 = connection.prepareStatement("insert into t_user(username,pwd) values(?,?)");

            ps1.setObject(1, "rjrj");
            ps1.setObject(2, 111222);

            ps1.execute();

            System.out.println("插入一个用户.....rjrj");

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ps2 = connection.prepareStatement("insert into t_user(username,pwd) values(?,?)");

            ps2.setObject(1, "rj2rj");
            ps2.setObject(2, 1121222);

            ps2.execute();

            System.out.println("插入一个用户.....rj2rj");







            connection.commit();



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
