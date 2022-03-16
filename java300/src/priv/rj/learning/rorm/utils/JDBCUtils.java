package priv.rj.learning.rorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装常用JDBC操作
 * @author rjjerry
 */
public class JDBCUtils {

    /**
     * 给ps传参
     * @param ps 与编译sql语句
     * @param params 参数
     */
    public static void handleParams(PreparedStatement ps, Object[] params){
        if (null != params){
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject(1 + i, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
