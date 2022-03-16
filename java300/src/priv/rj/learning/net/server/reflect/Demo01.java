package priv.rj.learning.net.server.reflect;

//获取结构信息Class对象（源头）
public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException {
       String string = "avb";
        //class 对象
        //对象.getClass()
        Class<?> clz = string.getClass();


        //类.class
        clz = String.class;
        //完整路径
        clz = Class.forName("priv.rj.learning.loadclass.java.lang.String");
    }
}
