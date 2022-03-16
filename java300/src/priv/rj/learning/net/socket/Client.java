package priv.rj.learning.net.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 必须先启动服务 后连接
 * 1. 创建客户端 必须指定服务器 + 端口 并在此时连接
 *  Socket(String host, int port)
 * 2. 接受数据 + 发送数据
 */
public class Client  {
    public static void main(String[] args) throws IOException {
        //1. 创建客户端 必须指定服务器 + 端口 并在此时连接
        Socket socket = new Socket("localhost", 8888);
        //2. 接受数据
       /*
        BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String echo = bf.readLine();
        System.out.println(echo);
        */

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String echo = dis.readUTF();
        System.out.println(socket.getLocalAddress().getHostName());
        System.out.println(socket.getPort());
        System.out.println(echo);
    }
}
