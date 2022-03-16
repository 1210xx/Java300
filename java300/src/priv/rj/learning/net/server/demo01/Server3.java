package priv.rj.learning.net.server.demo01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 创建服务，并启动
 *
 * 1. 请求
 * 2. 响应
 */
public class Server3 {

    private ServerSocket server;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
    public static void main(String[] args) {
        Server3 s1 = new Server3();
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
            StringBuilder responseContext = new StringBuilder();
            responseContext.append("<html><head> <title>HTTP响应示例</title> " +
                    "</head> <body>Hello Tomcat</body></html>");
            StringBuilder response = new StringBuilder();
            //1. Http协议版本，状态代码，描述
            response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
            //2. 响应头
            response.append("Server:Aparche Tomcat/6.0.12").append(CRLF);
            response.append("Date:").append(new Date()).append(CRLF);
            //正文长度：字节长度
            response.append("Content-Type:text/html;charset=utf8").append(CRLF);
            response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);
            //3. 正文之前
            response.append(CRLF);
            //4. 正文
            response.append(responseContext);

            //输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(response.toString());
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //停止server
    public void stop(){

    }
}
