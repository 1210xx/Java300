package priv.rj.learning.net.server.reflect;

import priv.rj.learning.net.server.demo02.Servlet;

/**
 * 创建实例 调用空构造
 */
public class Demo02 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clz = Class.forName("priv.rj.learning.server.demo03.LoginServlet");
        //调用空构造 确保 空的 构造器存在
        Servlet servlet =  (Servlet)clz.newInstance();
//        return servlet;

    }

}
