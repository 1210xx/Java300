package priv.rj.learning.net.chatroom.demo03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建服务器
 * 写出数据：输出流
 * 读取数据：输入流
 *
 * 接受的客户端存在先后顺序
 * 一个客户端完成在进行另一个
 */
public class Server {

    private List<MyChannel> all = new ArrayList<>();

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Server().start();
    }
    public void start() throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while (true){
            Socket client = server.accept();
            MyChannel channel = new MyChannel(client);
            all.add(channel);//统一管理
            new Thread(channel).start();// 一条道路
        }
    }

    /**
     * 一个客户端一条道路
     */
    private class MyChannel implements Runnable{
        private DataInputStream dis ;
        private DataOutputStream dos ;
        private boolean isRunning = true;
        public MyChannel(Socket client) {
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
//                e.printStackTrace();
                CloseUtil.closeAll(dis, dos);
                isRunning = false;
                all.remove(this);//移除自身
            }
        }

        /**
         * 读取数据
         * @return 返回数据
         */
        private String receive(){
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
//                e.printStackTrace();
                CloseUtil.closeAll(dis);
                isRunning = false;
                all.remove(this);//移除自身
            }
            return msg;
        }

        private void send(String msg){
            if (null == msg || msg.equals("")){
                return;
            }
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
//                e.printStackTrace();
                CloseUtil.closeAll(dos);
                isRunning = false;
                all.remove(this);//移除自身
            }
        }
        private void sendOthers(){
            String msg = receive();
            //遍历容器
            for (MyChannel other : all){
                if (this == other){
                    continue;
                }
                //发送给其他客户端、
                other.send(msg);
            }
        }

        @Override
        public void run() {
            while (isRunning){
                sendOthers();
            }
        }
    }
}
