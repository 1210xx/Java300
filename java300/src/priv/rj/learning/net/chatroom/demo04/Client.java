package priv.rj.learning.net.chatroom.demo04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 创建客户端 发送 接受 数据
 * 写出数据：输出流
 * 读取数据：输入流
 *
 *
 * 输入流 与 输出流在不同线程内
 *
 * 加入name用来辨认客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入名称： ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if (name.equals("")){
            return;
        }
        Socket client = new Socket("localhost", 9999);
        //控制台输入流
        new Thread(new Send(client,name)).start(); //一条路径
        new Thread(new Receive(client)).start();//一条路径
    }
}
