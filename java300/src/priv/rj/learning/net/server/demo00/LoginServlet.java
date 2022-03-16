package priv.rj.learning.net.server.demo00;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request req,Response rep) throws Exception {
		String name = req.getParameter("uname");
		String pwd =req.getParameter("pwd");
		if(login(name,pwd)){
			rep.println("��¼�ɹ�");
		}else{
			rep.println("��¼ʧ��");
		}
		
		
		
	}
	
	
	public boolean login(String name,String pwd){
		return name.equals("bjsxt") && pwd.equals("12346");
	}
	
	
	@Override
	public void doPost(Request req,Response rep) throws Exception {
		
	}

}
