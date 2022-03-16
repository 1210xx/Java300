package priv.rj.learning.loadclass;

/**
 * 线程上下文类加载器测试
 */
public class Demo05 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = Demo05.class.getClassLoader();
        System.out.println(loader);

        ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
        System.out.println(loader2);

        Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("/Users/rainjaneJerry/Downloads/java/myjava"));
        System.out.println(Thread.currentThread().getContextClassLoader());

        Class<Demo01> demo01Class  = (Class<Demo01>) Thread.currentThread().getContextClassLoader().loadClass("priv.rj.learning.loadclass.Demo01");
        System.out.println(demo01Class);
        System.out.println(demo01Class.getClassLoader());
    }
}
