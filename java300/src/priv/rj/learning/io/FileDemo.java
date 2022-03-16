package priv.rj.learning.io;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args)  {
//        ioFileDemo();
//        judgeInfoTest();
        try {
            createOrDeleteFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件操作失败");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void createOrDeleteFile() throws IOException, InterruptedException {
        String path = "/Users/rainjaneJerry/Documents/Others/Rj/MySource/rj2.jpg";
        File src = new File(path);
        if (!src.exists()){
            boolean flag = src.createNewFile();
            System.out.println(flag?"success":"fail");
        }
        boolean flag = src.delete();
        System.out.println(flag?"success":"fail");

        File temp = File.createTempFile("test",".temp",new File("/Users/rainjaneJerry/Documents/Others/Rj/MySource"));
        Thread.sleep(1000);
        temp.deleteOnExit();

    }
    public static void judgeInfoTest() {
        String path = "/Users/rainjaneJerry/Documents/Others/Rj/MySource/rj.jpg";
        File src = new File(path);
        System.out.println("文件是否存在：" + src.exists());
        System.out.println("文件是否可写：" + src.canWrite());
        System.out.println("文件是否可读：" + src.canRead());

        //不存在的默认为文件夹
        //isFile
        if (src.isFile()){
            System.out.println("文件");
        }else if(src.isDirectory()){//isDirectory
            System.out.println("文件夹");
        }else {
            System.out.println("文件不存在");
        }
        if (src.isAbsolute())
            System.out.println("是绝对路径");
        else
            System.out.println("相对路径");
        System.out.println(src.length());
    }

    public static void ioFileDemo() {
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);
        String path = "/Users/rainjaneJerry/Documents/Learn/Todo";
        System.out.println(path);
        String parentPath = "/Users/rainjaneJerry/Documents/Others/Rj/MySource";
        String name = "rj.jpg";
        File src = new File(parentPath, name);
        src = new File(new File(parentPath), name);
        System.out.println(src.getName());
        System.out.println(src.getPath());
        src = new File("/Users/rainjaneJerry/Documents/Others/Rj/MySource/rj.jpg");
        System.out.println(src.getName());
        System.out.println(src.getPath());
        src = new File("test.txt");
        System.out.println(src.getName());
        System.out.println(src.getPath());
        System.out.println(src.getAbsoluteFile());
    }
}
