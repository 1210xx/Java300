package priv.rj.learning.net.chatroom.demo04;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
    //输入流
    private DataInputStream dis;
    //线程 表示
    private boolean isRunning = true;

    public Receive() {

    }

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
//            e.printStackTrace();
            isRunning = false;
            priv.rj.learning.net.chatroom.demo03.CloseUtil.closeAll(dis);
        }
    }

    public String receive(){
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
//            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
        return msg;
    }
    @Override
    public void run() {
        while (isRunning) {
            System.out.println(receive());
        }
    }
}
