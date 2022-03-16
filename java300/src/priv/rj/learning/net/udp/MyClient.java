package priv.rj.learning.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 	1. 创建客户端 DatagramSocket 类 + 指定端口
 * 	2. 准备数据 字节数组
 * 	3. 打包 DatagramPacket + 服务器地址 即端口
 * 	4. 发送
 *  5. 释放资源
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(6666);
        String msg = "udp transfor 测试";
        byte[] data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        datagramSocket.send(packet);
        datagramSocket.close();

    }
}
