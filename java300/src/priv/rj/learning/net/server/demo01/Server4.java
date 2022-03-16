package priv.rj.learning.net.server.demo01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务，并启动
 *
 * 1. 请求
 * 2. 响应
 */
public class Server4 {

    private ServerSocket server;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
    public static void main(String[] args) {
        Server4 s1 = new Server4();
        s1.start();
    }

    /**
     * 启动方法
     */
    public void start(){
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
    private void receive(){

        try {
            Socket client = server.accept();
            //接受客户端的请求信息
            byte[] data = new byte[20480];
            int len = client.getInputStream().read(data);
            //接受客户端请求信息
            String requestInfo = new String(data, 0, len).trim();
            System.out.println(requestInfo);

            //响应
            Response rep = new Response(client.getOutputStream());
            rep.println("<html><head> <title>HTTP响应示例</title>");
            rep.println("</head> <body>Hello Client</body></html>");
            rep.pushToClient(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //停止server
    public void stop(){

    }
}
