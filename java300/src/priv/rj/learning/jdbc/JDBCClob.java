package priv.rj.learning.jdbc;

import java.io.Reader;
import java.sql.*;

/**
 * 测试Clob 文本大对象的使用
 * 将字符串、文件内容插入到数据库
 * 取出
 */
public class JDBCClob {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reader reader = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

           // ps = connection.prepareStatement("insert into t_user (username, myInfo) values (?, ?)");
            //ps.setString(1, "rjrjr");
          //  ps.setClob(2, new FileReader(new File("/Users/rainjaneJerry/Downloads/java/myjava/a.txt")));
            //将程序中的字符串插入到数据库Clob字段中
//            ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("aaaaaafsdfa".getBytes()))));
            ps = connection.prepareStatement("select * from t_user where id = ?");
            ps.setObject(1, 21022);


            rs = ps.executeQuery();

            while (rs.next()){
                Clob clob = rs.getClob("myInfo");
                reader = clob.getCharacterStream();
                int temp = 0;
                while (-1 != (temp = reader.read())){
                    System.out.print((char)temp);
                }
            }
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            try {
                if (null != reader){
                    reader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
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
                if (null != connection){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
