package priv.rj.learning.net.server.serverapp;

import priv.rj.learning.net.chatroom.demo04.CloseUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 封装相应消息
 */


public class Response {
    //头信息
    private StringBuilder headInfo;
    //正文长度
    private int len = 0;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
//正文
    private StringBuilder content;
    //构建流
    private BufferedWriter bw;

    public Response() {
        headInfo = new StringBuilder();
        content = new StringBuilder();
        len = 0;
    }
    public Response(OutputStream os) {
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }

    public Response(Socket client) {
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            headInfo = null;
        }
    }

    /**
     * 构建正文
     */
    public Response print(String info){
        content.append(info);
        len += info.getBytes().length;
        return this;
    }
    /**
     * 构建正文
     */
    public Response println(String info){
        content.append(info).append(CRLF);
        len += (info + CRLF).getBytes().length;
        return this;
    }


    /**
     * 构建响应信息
     */
    public void createHeadInfo(int code) {
        //1. Http协议版本，状态代码，描述
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch (code) {
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("Not Found");
                break;
            case 505:
                headInfo.append("Server Error");
                break;
        }
        headInfo.append(CRLF);
        //2. 响应头
        headInfo.append("Server:Rj server/6.0.12").append(CRLF);
        headInfo.append("Date:").append(new Date()).append(CRLF);
        //正文长度：字节长度
        headInfo.append("Content-Type:text/html;charset=utf8").append(CRLF);
        headInfo.append("Content-Length:").append(len).append(CRLF);
        //3. 正文之前
        headInfo.append(CRLF);
    }
    void pushToClient(int code) throws IOException {
        if (null == headInfo){
            code = 500;
        }
        createHeadInfo(code);
        //头信息+分隔符
        bw.append(headInfo.toString());
        //正文
        bw.append(content.toString());
        bw.flush();
    }
    public void close(){
        CloseUtil.closeAll(bw);
    }
}
