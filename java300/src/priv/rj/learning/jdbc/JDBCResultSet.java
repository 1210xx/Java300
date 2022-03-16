package priv.rj.learning.jdbc;

import java.sql.*;

/**
 * 测试ResultSet
 */
public class JDBCResultSet {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
            // 获 得 Preparedstatement 对象

            String sql = "select id,username,pwd from t_user where id > ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, 2);

            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()){
                System.out.println(resultset.getInt(1) + "----" + resultset.getString(2) + "------" + resultset.getString(3));
            }

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
