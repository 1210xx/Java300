package priv.rj.learning.rorm.core;

import priv.rj.learning.rorm.bean.Configuration;
import priv.rj.learning.rorm.pool.DBConnPool;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @author rjjerry
 */
public class DBManger {

    /**
     * Configuration 对象
     * 配置信息
     */
    private static Configuration conf;

    /**
     * 连接池对象
     */
    private static DBConnPool pool;

    /**
     * 静态代码块，在类加载的时候执行，只加载一次
     */
    static {
        Properties pros = new Properties();

        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("src/priv/rj/learning/rorm/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        conf = new Configuration();
        conf.setUsingDB(pros.getProperty("usingDB"));
        conf.setDriver(pros.getProperty("driver"));
        conf.setPoPackage(pros.getProperty("poPackage"));
        conf.setSrcPath(pros.getProperty("srcPath"));
        conf.setUrl(pros.getProperty("url"));
        conf.setUser(pros.getProperty("user"));
        conf.setPwd(pros.getProperty("pwd"));
        conf.setQueryClass(pros.getProperty("queryClass"));
        conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
        conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));


    }


//    public static void main(String[] args) {
//        DBManger dbManger = new DBManger();
//    }

    /**
     * 获得Configuration对象
     * @return conf对象
     */
    public static Configuration getConf(){
        return conf;
    }

    /**
     * 获得Connection对象
     * @return conn对象
     */
    public static Connection getConn(){

//        //直接获取conn对象
//        try {
//            Class.forName(conf.getDriver());
//            //直接建立连接，后续增加连接池
//            return DriverManager.getConnection(conf.getUrl(), conf.getUser(),conf.getPwd());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        //从池中取conn对象
        if (pool == null){
            pool = new DBConnPool();
        }
        return pool.getConnection();
    }

    /**
     * 创建Connection对象
     * @return conn对象
     */
    public static Connection createConn(){
        try {
            Class.forName(conf.getDriver());
            //直接建立连接，后续增加连接池
            return DriverManager.getConnection(conf.getUrl(), conf.getUser(),conf.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭传入的ResultSet,Statement,Connection对象
     * @param rs ResultSet对象
     * @param ps Statement对象
     * @param conn Connection对象
     */
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
//        try {
//            if (null != conn){
//                conn.close();
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        pool.close(conn);
    }

    /**
     * 关闭传入的Statement,Connection对象
     * @param ps Statement对象
     * @param conn Connection对象
     */
    public static void close(Statement ps, Connection conn){
        try {
            if (null != ps){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
//        try {
//            if (null != conn){
//                conn.close();
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        pool.close(conn);
    }

    /**
     * 关闭传入的ResultSet对象
     * @param rs ResultSet对象
     */
    public static void close(ResultSet rs){
        try {
            if (null != rs){
                rs.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 关闭传入Statement对象
     * @param ps Statement对象
     */

    public static void close(Statement ps){
        try {
            if (null != ps){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * 关闭传入的Connection对象
     * @param conn Connection对象
     */
    public static void close(Connection conn){
//        try {
//            if (null != conn){
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        pool.close(conn);
    }
}
