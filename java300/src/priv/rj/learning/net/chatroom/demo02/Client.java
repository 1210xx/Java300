package priv.rj.learning.net.chatroom.demo02;

import java.io.IOException;
import java.net.Socket;

/**
 * 创建客户端 发送 接受 数据
 * 写出数据：输出流
 * 读取数据：输入流
 *
 *
 * 输入流 与 输出流在不同线程内
 *
 *
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        //控制台输入流
        new Thread(new Send(client)).start(); //一条路径
        new Thread(new Receive(client)).start();//一条路径
    }
}
