package priv.rj.learning.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 测试使用JDBC util类
 */
public class JDBCUtilDemo {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getMysqlConn();

            preparedStatement = connection.prepareStatement("insert into t_user (username) values (?)");

            preparedStatement.setObject(1, "testutil");
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,preparedStatement, connection);
        }
    }
}
