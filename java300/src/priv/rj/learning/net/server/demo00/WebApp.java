package priv.rj.learning.net.server.demo00;

import java.util.Map;

public class WebApp {
	private static ServletContext contxt;
	static{
		contxt =new ServletContext();
		
		Map<String,String> mapping =contxt.getMapping();
		mapping.put("/login", "login");
		mapping.put("/log", "login");
		mapping.put("/reg", "register");
		
		Map<String,String> servlet =contxt.getServlet();
		servlet.put("login", "priv.rj.learning.net.server.demo00.LoginServlet");
		servlet.put("register", "priv.rj.net.learning.server.demo00.RegisterServlet");
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if((null==url)||(url=url.trim()).equals("")){
			return null;
		}
		//�����ַ���(����·��)��������
		
		//return contxt.getServlet().get(contxt.getMapping().get(url));
		String name=contxt.getServlet().get(contxt.getMapping().get(url));
		return (Servlet)Class.forName(name).newInstance();//ȷ���չ������
	}
}
