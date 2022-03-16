package priv.rj.learning.net.server.demo01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务，并启动
 * 1. 请求
 */
public class Server {

    private ServerSocket server;

    public static void main(String[] args) {
        Server s1 = new Server();
        s1.start();
    }

    /**
     * 启动方法
     */
    public void start(){
        try {
            server = new ServerSocket(8888);
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收客户端
     */
    private void receive(){
        try {
            Socket client = server.accept();
            //接受客户端的请求信息
            StringBuilder sb = new StringBuilder();
            String msg = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while ((msg = br.readLine()).length() > 0){
                sb.append(msg);
                sb.append("\r\n");
//                if (null == msg)
//                    break;
            }
            String requestInfo = sb.toString().trim();
            System.out.println(requestInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //停止server
    public void stop(){

    }
}
