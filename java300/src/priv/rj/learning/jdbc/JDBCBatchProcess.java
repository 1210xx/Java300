package priv.rj.learning.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

/**
 * Batch process
 */
public class JDBCBatchProcess {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        Resultset rs = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

            connection.setAutoCommit(false);


            long start = System.currentTimeMillis();

            statement = connection.createStatement();


            for (int i = 0; i < 20000; i++) {
                statement.addBatch("insert into t_user (username,pwd,regTime) values('rj" + i + "',666666,now()); ");
            }

            statement.executeBatch();

            connection.commit();
            long end = System.currentTimeMillis();
            System.out.println("插入20000条数据，耗时（毫秒）：" + (end - start));


        }catch (ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }finally {
            try {
                if (null != statement){
                    statement.close();
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
