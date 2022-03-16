package priv.rj.learning.net.server.demo03;

import java.util.Map;

public class WebApp {
    private static ServletContext context;
    static {
        context = new ServletContext();

        Map<String, String> mapping = context.getMapping();
        mapping.put("/login", "login");
        mapping.put("/log", "login");
        mapping.put("/reg", "register");

        Map<String, String> servlet = context.getServlet();
        servlet.put("login", "priv.rj.learning.server.demo03.LoginServlet");
        servlet.put("register", "priv.rj.learning.server.demo03.RegisterServelet");
    }

    public static Servlet getServlet(String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (null == url || (url.trim()).equals("")){
            return null;
        }
        //根据字符串（完整路线）创建对象
        //return context.getServlet().get(context.getMapping().get(url));
        String name = context.getServlet().get(context.getMapping().get(url));

        return (Servlet) Class.forName(name).newInstance();
    }
}
