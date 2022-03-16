package priv.rj.learning.net.server.demo01;


import priv.rj.learning.net.server.util.CloseUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * 一个请求与相应就对应一个此对象
 */
public class Dispatcher implements Runnable{
    private Socket client;
    private Request req;
    private Response rep;
    private int code = 200;

    public Dispatcher(Socket client) {
        this.client = client;
        try {
            req = new Request(client.getInputStream());
            rep = new Response(client.getOutputStream());
        } catch (IOException e) {
            //e.printStackTrace();
            code = 500;
            return;
        }

    }

    @Override
    public void run() {
        Servlet serv = new Servlet();
        serv.servive(req, rep);
        try {
            rep.pushToClient(code);
        } catch (IOException e) {
//            e.printStackTrace();

        }
        try {
            rep.pushToClient(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CloseUtil.closeSocket(client);
    }
}
