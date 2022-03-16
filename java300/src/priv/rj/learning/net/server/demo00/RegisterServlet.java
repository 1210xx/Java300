package priv.rj.learning.net.server.demo00;

public class RegisterServlet extends Servlet{

	@Override
	public void doGet(Request req,Response rep) throws Exception {
		
	}
	
	
	@Override
	public void doPost(Request req,Response rep) throws Exception {
		rep.println("<html><head><title>����ע��</title>");
		rep.println("</head><body>");
		rep.println("����û���Ϊ:"+req.getParameter("uname"));
		rep.println("</body></html>");
	}

}
