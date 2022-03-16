package priv.rj.learning.net.demo;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo01 {
    public static void main(String[] args) throws MalformedURLException {
        //绝对路径构建
        URL url = new URL("http://www.baidu.com:80/index.html#aa?uname=oauv");
        System.out.println("协议: " + url.getProtocol());
        System.out.println("Domain: " + url.getHost());
        System.out.println("Resource: " + url.getFile());
        System.out.println("Port: " + url.getPort());
        System.out.println("Absolute path : " + url.getPath());
        System.out.println("RefPoint:" + url.getRef());
        System.out.println("Parameter: " + url.getQuery());
        //相对路径构建
        url = new URL("http://www.baidu.com:80/a/");
        url = new URL(url, "b.txt");
        System.out.println(url);
    }
}
