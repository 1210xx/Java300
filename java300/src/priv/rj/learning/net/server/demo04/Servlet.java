package priv.rj.learning.net.server.demo04;

/**
 * 抽象成一个父类
 */
public abstract class Servlet {
    public void servive(Request req, Response rep) throws Exception{
       this.doGet(req, rep);
       this.doPost(req, rep);
    }

    public abstract void doGet(Request req, Response rep) throws Exception;
    public abstract void doPost(Request req, Response rep) throws Exception;

}
