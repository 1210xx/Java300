package priv.rj.learning.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试执行SQL语句
 * Statement 的execute()
 */
public class JDBCExecuteSQL {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
            // 获 得 statement 对象
            statement = connection.createStatement();
//            //sql语句
//            String sqlStr = "insert into t_user(username,pwd,regTime) values('六六六',5566533,now())";
//            // 执行
//            statement.execute(sqlStr);

            //测试sql注入
            //可以随便修改数据库
            String id = "5 or 1 = 1";
            String sql = "delete from t_user where id = " + id;
            statement.execute(sql);

        } catch (ClassNotFoundException | SQLException e) {
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
