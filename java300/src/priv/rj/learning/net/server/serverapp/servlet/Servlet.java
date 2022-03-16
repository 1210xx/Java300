package priv.rj.learning.net.server.serverapp.servlet;

import priv.rj.learning.net.server.serverapp.Request;
import priv.rj.learning.net.server.serverapp.Response;

/**
 * 抽象成一个父类
 */
public abstract class Servlet {
    public void servive(Request req, Response rep) throws Exception{
       this.doGet(req, rep);
       this.doPost(req, rep);
    }

    protected abstract void doGet(Request req, Response rep) throws Exception;
    protected abstract void doPost(Request req, Response rep) throws Exception;

}
