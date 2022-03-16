package priv.rj.learning.io;

import java.io.*;

import static java.io.FileDescriptor.out;

public class SystemIODemo {
    public static void main(String[] args) throws IOException {
        test2();
    }

    public static void test2() throws IOException {
        InputStream inputStream = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println("enter: ");
        String msg = bufferedReader.readLine();
        System.out.println(msg);

    }

    public static void test1() throws FileNotFoundException {
        //重定向
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("java300/src/priv/rj/learning/io/test/systemio.txt")), true));
        System.out.println("test");
        System.out.println("aaa");
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(out)), true));
        System.out.println("back");
    }
}
