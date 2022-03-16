package priv.rj.learning.loadclass;

/**
 * 测试自定义的类加载器FileSystemClassLoader
 */
public class Demo03 {
    public static void main(String[] args) throws ClassNotFoundException {
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader("/Users/rainjaneJerry/Downloads/java/myjava");
        FileSystemClassLoader fileSystemClassLoader2 = new FileSystemClassLoader("/Users/rainjaneJerry/Downloads/java/myjava");

        Class<?> c = fileSystemClassLoader.loadClass("HelloWorld");
        Class<?> c2 = fileSystemClassLoader.loadClass("HelloWorld");

        Class<?> c3 = fileSystemClassLoader2.loadClass("HelloWorld");

        Class<?> c4 = fileSystemClassLoader2.loadClass("java.lang.String");

        Class<?> c5 = fileSystemClassLoader2.loadClass("priv.rj.learning.loadclass.Demo01");

        Class<?> c6 = fileSystemClassLoader2.loadClass("priv.rj.learning.dynamiccompile.Demo01");
        //同一个类被不同的加载器加载，JVM认为是不同的类
        System.out.println(c.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        //自定义加载器
        System.out.println(c3.getClassLoader());
        System.out.println(c4.hashCode());
        //null 引导类加载器
        System.out.println(c4.getClassLoader());
        //系统默认加载器
        System.out.println(c5.getClassLoader());
        //系统默认加载器（不同的model加载器一样）
        System.out.println(c6.getClassLoader());

        /**
         * idea 中一个项目只有一个类加载器，
         * 应该是通过不同的路径区分的。
         */
    }
}
