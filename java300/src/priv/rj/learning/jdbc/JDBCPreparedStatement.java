package priv.rj.learning.jdbc;

import java.sql.*;

/**
 * 使用PreparedStatement执行execute
 */
public class JDBCPreparedStatement {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
            // 获 得 Preparedstatement 对象
            //sql语句 ? 占位符 有预处理机制
            String sqlStr = "insert into t_user (username,pwd,regTime) values (?,?,?)";
            // PreparedStatement 执行
            PreparedStatement ps = connection.prepareStatement(sqlStr);
//            ps.setString(1, "任杰");
//
//            ps.setString(2, "11231");

            //可以使用object判断参数类型
            ps.setObject(1, "rj4");
            ps.setObject(2, "1231332");
            ps.setObject(3, new java.sql.Date(System.currentTimeMillis()));
            System.out.println("插入一行记录");
            System.out.println(ps.executeUpdate());


        }catch (ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }finally {
            try {
                if (null != preparedStatement){
                    preparedStatement.close();
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
