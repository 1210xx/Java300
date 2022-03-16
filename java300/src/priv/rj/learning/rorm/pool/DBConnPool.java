package priv.rj.learning.rorm.pool;

import priv.rj.learning.rorm.core.DBManger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接的池
 * @author rjjerry
 */
public class DBConnPool {
    /**
     * 连接池对象
     */
    private  List<Connection> pool;

    /**
     * 最大连接数
     */
    private static final int POOL_MAX_SIZE = DBManger.getConf().getPoolMaxSize();

    /**
     * 最小连接数
     */
    private static final int POOL_MIN_SIZE = DBManger.getConf().getPoolMinSize();

    /**
     * 初始化连接池，池中的连接数大到最小值
     */
    public void initPool(){
        if (pool == null){
            pool = new ArrayList();
        }

        while (pool.size() < DBConnPool.POOL_MIN_SIZE){
            pool.add(DBManger.createConn());
            System.out.println("初始化池，池中连接数:" + pool.size());
        }
    }

    /**
     * 从池中获取连接对象
     * @return 最后一个连接对象
     */
    public synchronized Connection getConnection(){
        int last_index = pool.size() - 1;
        Connection conn = pool.get(last_index);
        pool.remove(last_index);
        return conn;
    }

    /**
     * 关闭Connection ，将连接放入池中
     * @param conn
     */
    public synchronized void close(Connection conn){
        if (pool.size() >= POOL_MAX_SIZE){
            try {
                if (null != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            pool.add(conn);
        }
    }

    public DBConnPool() {
        initPool();
    }
}
