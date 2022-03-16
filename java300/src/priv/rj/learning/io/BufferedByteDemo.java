package priv.rj.learning.io;

import java.io.*;

public class BufferedByteDemo {
    /**
     * copy file
     * 字节流文件拷贝+缓冲流
     * 提高性能：
     * 缓冲流（节点流）
     * @throws IOException
     */
    public static void copyFileIO() throws FileNotFoundException,IOException {
        //1. 建立联系
        File src = new File("java300/src/priv/rj/learning/io/test.jpg");
        File dest = new File("java300/src/priv/rj/learning/io/testcopy.jpg");
        //2. 选择流
        InputStream inputStream = new BufferedInputStream(new FileInputStream(src));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dest));
        //3.1 读取
        byte[] flush = new byte[1024];
        int Length = 0;
        //read
        while ((Length = inputStream.read(flush)) != -1) {
            //3.2 写入
            outputStream.write(flush, 0, Length);
        }
        outputStream.flush();

        //4. 关闭
        outputStream.close();
        inputStream.close();
    }

}
