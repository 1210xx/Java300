package priv.rj.learning.io;


import java.io.*;

public class CharIODemo {
    public static void main(String[] args) {
//        charWrite();
        copyFile_txt();

    }

    public static void charRead() {
        File src = new File("java300/src/priv/rj/learning/io/test.txt");
        Reader reader = null;
        try {
            reader = new FileReader(src);
            int len = 0;
            char[] flush = new char[1024];
            while ((len = reader.read(flush)) != -1) {
                String string = new String(flush, 0, len);
                System.out.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void charWrite() {

        File dest = new File("java300/src/priv/rj/learning/io/test/test2.txt");
        Writer writer = null;

        try {
            writer = new FileWriter(dest);
            String msg = "锄禾日当吴\r\n\t+ 汗滴禾下土\n + 粒粒皆辛苦ren jie  da ren jie\n";
            writer.write(msg);
            writer.append("aasdfasvhhosauiho");
            writer.flush();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void copyFile_txt() {
        String srcPath = "java300/src/priv/rj/learning/io/test/test2.txt";
        String destPath = "java300/src/priv/rj/learning/io/test/testcopytext.txt";
        Reader reader = null;
        Writer writer = null;

        try {
            reader = new FileReader(srcPath);
            writer = new FileWriter(destPath);
            int len = 0;
            char[] readerArray = new char[1024];
            while (((len = reader.read(readerArray)) != -1)) {
                writer.write(readerArray, 0, len);
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
