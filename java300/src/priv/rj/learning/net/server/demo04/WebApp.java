package priv.rj.learning.net.server.demo04;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;
import java.util.Map;

public class WebApp {
    private static ServletContext context;
    static {
        try {
            context = new ServletContext();
            //获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //从工厂获取解析器
            SAXParser sax = factory.newSAXParser();
            //指定xml处理器
            WebHandler web = new WebHandler();
            sax.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("priv/rj/learning/net/server/demo04/web.xml"), web);
            //将list转成map
            context = new ServletContext();
            Map<String, String> servlet = context.getServlet();
            //servlet-name servlet-clz
            for (Entity entity : web.getEntityList()){
                servlet.put(entity.getName(), entity.getClz());
            }
            //url-pattern  servlet-name
            Map<String, String> mapping = context.getMapping();
            for (Mapping mapping1 : web.getMappingList()){
                List<String> urls = mapping1.getUrlPattern();
                for (String url : urls){
                    mapping.put(url, mapping1.getName());
                }
            }
        }catch (Exception e){
        }
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
