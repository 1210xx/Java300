package priv.rj.learning.net.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 	1. 创建服务器端 DatagramSocket + 指定端口
 * 	2. 准备接收容器 字节数组 ---> double 类型
 * 	3. 封装 DatagramPacket 包
 * 	3. 接收数据
 * 	4. 分析 double 类型
 *  5. 释放资源
 */
public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        byte[] container = new byte[1024];
        DatagramPacket packet = new DatagramPacket(container, container.length);
        datagramSocket.receive(packet);
        Double data = convert(packet.getData());
        int len = packet.getLength();
        System.out.println(data);
        datagramSocket.close();
    }

    public static double convert(byte[] data) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        double num = dis.readDouble();
        dis.close();
        return num;
    }
}
