package priv.rj.learning.loadclass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 加密工具类
 */
public class EncrptUtil {


    public static void main(String[] args) {
        encrpt("/Users/rainjaneJerry/Downloads/java/myjava/HelloWorld.class", "/Users/rainjaneJerry/Downloads/java/myjava/temp/HelloWorld.class");
    }

    public static void encrpt(String src, String dest){
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            int len = -1;
            while (-1 !=(len = fis.read())){
                fos.write(len^0xff);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try {
                if (fos != null){
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }
}
