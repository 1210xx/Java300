package priv.rj.learning.net.server.demo02;

public class LoginServlet extends Servlet{
    @Override
    public void doGet(Request req, Response rep) throws Exception {
        String name = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        if (login(name, pwd)){
            rep.println("登录成功");
        }else {
            rep.print("登录失败");
        }
    }

    public boolean login(String name, String pwd){
        return name.equals("renjie") && pwd.equals("123");
    }



    @Override
    public void doPost(Request req, Response rep) throws Exception {

    }


}
