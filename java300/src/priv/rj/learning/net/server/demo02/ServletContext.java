package priv.rj.learning.net.server.demo02;

import java.util.HashMap;
import java.util.Map;
//上下文
//类似工厂模式
public class ServletContext {
    //为每一个servlet取别名
    //login --> LoginServlet
    private Map<String, Servlet> servlet;
    //url --> login
    // /log --> login
    // /login --> login
    private Map<String, String> mapping;

    public ServletContext() {
        servlet = new HashMap<>();
        mapping = new HashMap<>();
    }

    public Map<String, Servlet> getServlet() {
        return servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setServlet(Map<String, Servlet> servlet) {
        this.servlet = servlet;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
