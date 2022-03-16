package priv.rj.learning.net.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 	1. 创建客户端 DatagramSocket 类 + 指定端口
 * 	2. 准备数据  字节数组 ---> double 类型
 * 	3. 打包 DatagramPacket + 服务器地址 即端口
 * 	4. 发送
 *  5. 释放资源
 */
public class Client {
    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(6666);
        String msg = "udp transfor 测试";
        double num = 89.12;

        byte[] data = convert(num);
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        datagramSocket.send(packet);
        datagramSocket.close();
    }

    public static byte[] convert(double num) throws IOException {
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(num);
        dos.flush();
        data = bos.toByteArray();
        dos.close();
        return data;
    }
}
