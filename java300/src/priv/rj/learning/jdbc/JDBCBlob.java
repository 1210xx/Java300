package priv.rj.learning.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

/**
 * 测试Blob 二进制大对象
 */
public class JDBCBlob {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
//
//            ps = connection.prepareStatement("insert into t_user (username, headImg) values (?, ?)");
//
//            ps.setString(1, "rjrjrb");
//
//            ps.setBlob(2, new FileInputStream("/Users/rainjaneJerry/Downloads/java/myjava/icon.jpg"));
//            ps.execute();


            ps = connection.prepareStatement("select * from t_user where id = ?");
            ps.setObject(1, 21024);

            rs = ps.executeQuery();

            while (rs.next()) {
                Blob blob = rs.getBlob("headImg");
                is = blob.getBinaryStream();
                os = new FileOutputStream("/Users/rainjaneJerry/Downloads/java/myjava/b.jpg");
                int temp = 0;
                while (-1 != (temp = is.read())) {
                    os.write(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != is) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != ps) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

