package priv.rj.learning.net.server.demo01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server6 {

    private ServerSocket server;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";

    public static void main(String[] args) {
        Server6 s1 = new Server6();
        s1.start();
    }

    /**
     * 启动方法
     */
    public void start() {
        try {
            server = new ServerSocket(8889);
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收客户端
     */
    private void receive() {

        try {
            Socket client = server.accept();
            //请求
            Servlet serv = new Servlet();
            Request req = new Request(client.getInputStream());
            Response rep = new Response(client.getOutputStream());
            serv.servive(req, rep);
            rep.pushToClient(200);
            //响应

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //停止server
    public void stop() {

    }


}
