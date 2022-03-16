package priv.rj.learning.net.server.serverapp.servlet;

import priv.rj.learning.net.server.serverapp.Request;
import priv.rj.learning.net.server.serverapp.Response;

public class LoginWeb extends Servlet {
    @Override
    public void doGet(Request req, Response rep) throws Exception {
        rep.print("success.....");
    }

    @Override
    public void doPost(Request req, Response rep) throws Exception {

    }
}
