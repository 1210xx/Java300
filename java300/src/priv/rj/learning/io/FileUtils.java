package priv.rj.learning.io;

import java.io.*;

public class FileUtils {

    /**
     * copy file
     * @param srcString: 源文件地址
     * @param destString: 目的文件地址
     * @throws IOException
     */
    public static void copyFileIO(String srcString, String destString) throws IOException {
        copyFileIO(new File(srcString), new File(destString));
    }

    public static void copyFileIO(File src, File dest)throws IOException{
        if (! src.isFile()){
            throw new IOException("只能拷贝文件");
        }
        if (dest.isDirectory()) {
            System.out.println("不能建立文件夹同名的文件");
            throw new IOException(dest.getAbsolutePath() + " 不能建立文件夹同名的文件");
        }
        //2. 选择流
        InputStream inputStream = new BufferedInputStream(new FileInputStream(src));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dest));
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

    public static void copyDir(String srcPath, String destPath){
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        copyDir(srcFile, destFile);

    }

    /**
     * copy direction
     * @param srcFile
     * @param destFile
     */
    public static void copyDir(File srcFile, File destFile){
        if (srcFile.isDirectory()){
            destFile = new File(destFile, srcFile.getName());
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


