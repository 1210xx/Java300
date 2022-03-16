package priv.rj.learning.io;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {
    public static void close(Closeable ...closeables){
        for (Closeable c: closeables
             ) {
            if (null != c){
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T extends Closeable> void closeAll(T ... ts){
        for (T t: ts
        ) {
            if (null != t){
                try {
                    t.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
