package priv.rj.learning.io;

import java.io.File;
import java.io.IOException;

public class DirCopyDemo {
    public static void main(String[] args) {
        String srcPath = "java300/src/priv/rj/learning/io/test";

        String destPath = "java300/src/priv/rj/learning/test";

        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        copyDirDetail(srcFile, destFile);
    }
    public static void copyDir(String srcPath, String destPath){
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        copyDir(srcFile, destFile);

    }


    public static void copyDir(File srcFile, File destFile){
        if (srcFile.isDirectory()){
            destFile = new File(destFile, srcFile.getName());
            if (destFile.getAbsolutePath().contains(srcFile.getAbsolutePath())){
                System.out.println("父目录不能拷贝到子目录中 ");
                return;
            }
        }
        copyDirDetail(srcFile, destFile);

    }

    public static void copyDirDetail(File srcFile, File destFile) {
        if (srcFile.isFile()) {
            try {
                FileUtils.copyFileIO(srcFile, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (srcFile.isDirectory()) {
            destFile.mkdirs();
            for (File sub :
                    srcFile.listFiles()) {
                copyDirDetail(sub, new File(destFile, sub.getName()));
            }
        }
    }
}
