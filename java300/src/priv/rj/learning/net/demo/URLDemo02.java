package priv.rj.learning.net.demo;



import java.io.*;
import java.net.URL;

public class URLDemo02 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");

//        InputStream inputStream = url.openStream();
//        byte[] flush = new byte[35536];
//        int len = 0;
//        while (-1 != (len = inputStream.read(flush))){
//            System.out.println(new String(flush,0, len));
//        }
//        inputStream.close();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"), "utf-8"));

        String msg = null;
        while (null != (msg = bufferedReader.readLine())) {
            System.out.println(msg);
            bufferedWriter.append(msg);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
