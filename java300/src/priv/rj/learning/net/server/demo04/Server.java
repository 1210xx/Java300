package priv.rj.learning.net.server.demo04;

import priv.rj.learning.net.server.util.CloseUtil;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private ServerSocket server;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
    private boolean isShutDown = false;

    public static void main(String[] args) {
        Server s1 = new Server();
        s1.start();
    }

    /**
     * 启动方法
     */
    public void start() {
        start(8899);

    }

    public void start(int port) {
        try {
            server = new ServerSocket(port);
            this.receive();
        } catch (IOException e) {
//            e.printStackTrace();
            stop();
        }

    }

    /**
     * 接收客户端
     */
    private void receive() {

        try {
            while (!isShutDown){
                new Thread(new Dispatcher(server.accept())).start();
            }
        } catch (IOException e) {
//            e.printStackTrace();
            stop();
        }
    }

    //停止server
    public void stop() {
        isShutDown= true;
        CloseUtil.closeSocket(server);
    }


}
