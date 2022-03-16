package priv.rj.learning.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 */
public class JDBCUtil {

    //读取资源文件的信息
    static Properties pros = null;

    //加载JDBCUtil类的时候调用
    static {
            pros = new Properties();

        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("src/priv/rj/learning/jdbc/jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConn(){
        try {
            //加载驱动类
            Class.forName(pros.getProperty("mysqlDriver"));
            //建立连接
            return DriverManager.getConnection(pros.getProperty("mysqlURL"), pros.getProperty("mysqlUser"), pros.getProperty("mysqlPwd"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(ResultSet rs, Statement ps, Connection conn){
        try {
            if (null != rs){
                rs.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (null != ps){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if (null != conn){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs){
        try {
            if (null != rs){
                rs.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement ps){
        try {
            if (null != ps){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(Connection conn){
        try {
            if (null != conn){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
