package priv.rj.learning.io;

import java.io.*;

public class ProcessStreamConvertDemo {
    public static void main(String[] args) throws IOException {
        convertStream();
    }

    public static void convertStream() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(new File("java300/src/priv/rj/learning/io/test/test.txt"))
                , "utf-8"));
        //写出文件
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(new File("java300/src/priv/rj/learning/io/test/testbufferedoutputstram.txt") )
                )
        );

        String info = null;
        while (null != (info = bufferedReader.readLine())){
            System.out.println(info);
            bufferedWriter.write(info);
        }
        bufferedWriter.flush();
        bufferedReader.close();
    }
    public static void encodeCovert(){
        String string = "中国";
        byte[] data = string.getBytes();
        //字符长度不够
        System.out.println(new String(data, 0 , 5));
    }
    public static void test1() throws UnsupportedEncodingException {
        //解码 byte --> char  utf-8 default
        String string = "中跟哦";
        //编码 char --> byte
        byte[] data = string.getBytes();
        //编码与解码的字符集统一
        System.out.println(new String(data));
        //不统一出现乱码
        data = string.getBytes("gbk");
        System.out.println(new String(data));

        byte[] data2 = string.getBytes("gbk");
        string = new String(data2, "gbk");
        System.out.println(string);

    }
}
