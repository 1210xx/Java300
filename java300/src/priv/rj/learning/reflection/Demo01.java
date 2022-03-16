package priv.rj.learning.reflection;

/**
 * 测试java.lang.Class获取方式
 * @author rjjerry
 */
public class Demo01 {
    public static void main(String[] args) {
        String path = "priv.rj.learning.reflection.bean.User";
        try {
            //对象是用来表示和封装一些数据。一个类被加载后，JVM会创建一个对应类的Class对象。类的整个结构信息会放到对应的Class对象中
            //这个Class对象就像一面镜子一样，通过镜子可以看到对应类的全部信息。
            Class<?> clazz = Class.forName(path);
            System.out.println(clazz);
            Class c = String.class;
            Class c2 = path.getClass();
            Class c3 = int.class;
            Class c4 = Demo01.class;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
