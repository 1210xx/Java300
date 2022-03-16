package priv.rj.learning.loadclass;

/**
 * 测试简单的加密解密（取反）操作
 */
public class Demo04 {
    public static void main(String[] args) throws ClassNotFoundException {
        //测试取反操作
//        int a = 3;
//        System.out.println(Integer.toBinaryString(a^0xff));

        //加密后的class文件，正常的加载器无法加载，报ClassFormatError
//        FileSystemClassLoader loader = new FileSystemClassLoader("/Users/rainjaneJerry/Downloads/java/myjava/temp");
//
//        Class<?> c = loader.loadClass("HelloWorld");
//        System.out.println(c);

        DecrptClassLoader loader = new DecrptClassLoader("/Users/rainjaneJerry/Downloads/java/myjava/temp");
        Class<?> c = loader.loadClass("HelloWorld");
        System.out.println(c);
    }
}
