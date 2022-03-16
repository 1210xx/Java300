package priv.rj.learning.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 测试与数据建立连接
 */
public class JDBCTest01 {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            long start = System.currentTimeMillis();
            //建立连接(连接对象内部包含了Socket对象，是一个远程的连接。比较耗时，这是Connection对象的一个要点。)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象。
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            long end = System.currentTimeMillis();
            System.out.println(connection);
            System.out.println("建立连接耗时：" + (end - start)+ "ms");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
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
