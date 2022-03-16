package priv.rj.learning.net.chatroom.demo02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器
 * 写出数据：输出流
 * 读取数据：输入流
 *
 * 接受的客户端存在先后顺序
 * 一个客户端完成在进行另一个
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            Socket client = server.accept();
            //输入流
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            while (true) {
                String msg = dis.readUTF();
                System.out.println(msg);
                //输出流
                dos.writeUTF("服务器---->" + msg);
                dos.flush();
            }
        }
    }
}
