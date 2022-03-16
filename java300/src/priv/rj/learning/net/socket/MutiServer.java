package priv.rj.learning.net.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1. 创建服务器，指定端口
 * 2. 接受客户端的客户端的连接 阻塞式
 * 3. 发送数据 + 接受数据
 *
 * 接受多个客户端
 */
public class MutiServer {
    public static void main(String[] args) throws IOException {
        //1. 创建服务器，指定端口
        ServerSocket serverSocket = new ServerSocket(8888);
        // 2. 接受客户端的客户端的连接 阻塞式
        int i = 0;
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("num: "+ i++ + " client connected");
            //3. 发送数据
            String msg = "Welcome to use server";
            //输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(msg);
            dos.flush();


        }
    }
}
