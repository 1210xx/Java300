package priv.rj.learning.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 	1. 创建服务器端 DatagramSocket + 指定端口
 * 	2. 准备接收容器 字节数组
 * 	3. 封装 DatagramPacket 包
 * 	3. 接收数据
 * 	4. 分析
 *  5. 释放资源
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        byte[] container = new byte[1024];
        DatagramPacket packet = new DatagramPacket(container, container.length);
        datagramSocket.receive(packet);
        byte[] data = packet.getData();
        int len = packet.getLength();
        System.out.println(new String(data, 0, len));
        datagramSocket.close();
    }
}
