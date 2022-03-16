package priv.rj.learning.io;

import java.io.*;

/**
 * 文件 -- 程序 --> 字节数组
 * 文件输入流    字节数组输出流
 * 字节数组 -- 程序 --> 文件
 * 字节数组输出流    文件输出流
 */
public class ByteArrayDemo {
    public static void main(String[] args) throws IOException {
        byte[] data = getByteFromFile("java300/src/priv/rj/learning/io/test/test.txt");
        System.out.println(new String(data));
        toFileFromByte(data, "java300/src/priv/rj/learning/io/test/testbytearr.txt");
    }


    public static void toFileFromByte(byte[] src, String destPath) throws IOException {
        File dest = new File(destPath);
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(src));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dest));
        byte[] flush = new byte[1024];
        int len = 0;
        while (-1 != (len = inputStream.read(flush))){
outputStream.write(flush, 0, len);
        }
        outputStream.flush();

        outputStream.close();
        inputStream.close();
    }
    public static byte[] getByteFromFile(String srcPath) throws IOException {
        // 创建源
        File src = new File(srcPath);
        //创建字节数组目的地
        byte[] dest = null;

        InputStream inputStream  = new BufferedInputStream(new FileInputStream(src));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //操作 不断读取文件 写出到字节数组
        byte[] flush = new byte[1024];
        int len = 0;
        while (-1 != (len = inputStream.read(flush))){
            byteArrayOutputStream.write(flush, 0, len);
        }
        byteArrayOutputStream.flush();

        dest = byteArrayOutputStream.toByteArray();

        byteArrayOutputStream.close();
        inputStream.close();

        return dest;
    }
}
