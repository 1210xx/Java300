package priv.rj.learning.net.server.demo01;

public class Servlet {
    public void servive(Request req, Response rep){
        rep.println("<html><head> <title>HTTP响应示例</title>");
        rep.println("</head> <body>");
        rep.println("欢迎：").println(req.getParameter("uname")).println("回来");
        rep.println("</body></html>");
    }
}
