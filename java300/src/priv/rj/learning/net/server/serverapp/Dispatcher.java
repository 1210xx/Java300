package priv.rj.learning.net.server.serverapp;


import priv.rj.learning.net.server.serverapp.servlet.Servlet;
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

        try {
            Servlet serv = WebApp.getServlet(req.getUrl());
            if (null == serv){
                this.code = 404;

            }else {
            serv.servive(req,rep);
            }
            rep.pushToClient(code);
        } catch (Exception e) {
//            e.printStackTrace();
            this.code = 500;
        }
        try {
            rep.pushToClient(500);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CloseUtil.closeSocket(client);
    }
}
