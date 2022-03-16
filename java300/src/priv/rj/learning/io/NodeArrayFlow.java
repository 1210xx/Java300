package priv.rj.learning.io;

import java.io.*;

public class NodeArrayFlow {
    public static void main(String[] args) throws IOException {
            nodeRead(noteWrite() );
        System.out.println(new String(noteWrite()));
    }

    public static byte[] noteWrite() throws IOException {
        //destination
        byte[] dest;
        //选择流 不同点 不使用多台
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //操作 写出
        String msg = "操作与文件 输入操作不一致";
        byte[] info = msg.getBytes();
        byteArrayOutputStream.write(info, 0, info.length);
        //获取数据
        dest = byteArrayOutputStream.toByteArray();
        //释放资源
        byteArrayOutputStream.close();
        return dest;
     }

    public static void nodeRead(byte[] src) throws IOException {

        InputStream inputStream = new BufferedInputStream(
                new ByteArrayInputStream(
                        src
                )
        );

        byte[] flush = new byte[1024];
        int len = 0;
        while (-1 !=(len = inputStream.read(flush))){
            System.out.println(new String(flush, 0 , len));
        }
        inputStream.close();
    }
}
