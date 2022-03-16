package priv.rj.learning.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccess = new RandomAccessFile(new File("java300/src/priv/rj/learning/io/test/test.txt"),"r");
        randomAccess.seek(10);

        byte[] flush = new byte[1024];
        int len = 0;
        while (-1 != (len = randomAccess.read(flush))){
            if (len >= 20){
                System.out.println(new String(flush, 0, 20));
                break;
            }else
            System.out.println(new String(flush, 0, len));
        }

        randomAccess.close();
    }
}
