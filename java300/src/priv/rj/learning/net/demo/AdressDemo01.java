package priv.rj.learning.net.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AdressDemo01 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

        address = InetAddress.getByName("www.163.com");
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

        address = InetAddress.getByName("61.135.253.15");
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
    }
}
