package priv.rj.learning.io;

import java.io.*;

public class IOStreamDemo {
    public static void main(String[] args) throws IOException {
//        writeFileByByte();
        copyFileIO();
    }

    /**
     * copy file
     * @throws IOException
     */
    public static void copyFileIO() throws IOException {
        //1. 建立联系
        File src = new File("java300/src/priv/rj/learning/io/test.jpg");
        File dest = new File("java300/src/priv/rj/learning/io/testcopy.jpg");
        //2. 选择流
        InputStream inputStream = new FileInputStream(src);
        OutputStream outputStream = new FileOutputStream(dest);
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


    public static void readFileByByte(){
        File src = new File("java300/src/priv/rj/learning/io/test.txt");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(src);
            byte[] cache = new byte[10];
            int len = 0;
            while ((len = inputStream.read(cache) )!= -1 ){
                String info = new String(cache, 0, len);
                System.out.println(info);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取文件失败");
            e.printStackTrace();
        }finally {
            if (inputStream == null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("关闭输入流失败");
                    e.printStackTrace();
                }
            }
        }
    }
    public static void writeFileByByte(){
        File dest = new File("java300/src/priv/rj/learning/io/test.txt");
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(dest, true);
            String string = "ren jie  da ren jie\r\n";
            byte[] data = string.getBytes();
            outputStream.write(data, 0, data.length);

            outputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件写出失败");
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("文件关闭失败");
                    e.printStackTrace();
                }
            }
        }

    }
}
