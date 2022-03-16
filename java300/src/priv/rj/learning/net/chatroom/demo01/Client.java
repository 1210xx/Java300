package priv.rj.learning.net.chatroom.demo01;

import java.io.*;
import java.net.Socket;

/**
 * 创建客户端 发送 接受 数据
 * 写出数据：输出流
 * 读取数据：输入流
 *
 *
 * 输入流 与 输出流在同一线程内
 *
 *
 */
public class Client {


    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        //控制台输入流
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        while (true) {
            String info = console.readLine();
            //输出流
            dos.writeUTF(info);
            dos.flush();
            //输入流
            String msg = dis.readUTF();
            System.out.println(msg);

        }
    }
}
