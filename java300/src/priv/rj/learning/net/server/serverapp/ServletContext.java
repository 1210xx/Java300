package priv.rj.learning.net.server.serverapp;

import java.util.HashMap;
import java.util.Map;

//上下文
//类似工厂模式
public class ServletContext {
    //为每一个servlet取别名
    //login --> priv.rj.learning.server.demo03.LoginServlet
    private Map<String, String> servlet;
    //url --> login
    // /log --> login
    // /login --> login
    private Map<String, String> mapping;

    public ServletContext() {
        servlet = new HashMap<>();
        mapping = new HashMap<>();
    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setServlet(Map<String, String> servlet) {
        this.servlet = servlet;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
