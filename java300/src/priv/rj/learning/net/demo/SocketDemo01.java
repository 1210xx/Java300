package priv.rj.learning.net.demo;


import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 封装端口：在InetAddress基础上+端口
 */
public class SocketDemo01 {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        System.out.println(address.getHostName());
        System.out.println(address.getPort());

        InetAddress address1 = address.getAddress();
        System.out.println(address1.getHostAddress());
        System.out.println(address1.getHostName());


    }

}
