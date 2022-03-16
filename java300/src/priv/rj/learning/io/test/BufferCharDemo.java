package priv.rj.learning.io.test;

import java.io.*;

/**
 * 字符缓冲流+新增方法（不能发生多态）
 */
public class BufferCharDemo {
    public static void main(String[] args) {
        copyFile_txt();
    }
    public static void copyFile_txt() {
        String srcPath = "java300/src/priv/rj/learning/io/IOStreamDemo.java";
        String destPath = "java300/src/priv/rj/learning/io/test/testcopytext_buffer.txt";
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(srcPath));
            writer = new BufferedWriter(new FileWriter(destPath));
            /*
            int len = 0;
            char[] readerArray = new char[1024];
            while (((len = reader.read(readerArray)) != -1)) {
                writer.write(readerArray, 0, len);
            }
             */
            //新增
            String line = null;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
