package priv.rj.learning.map;


import java.lang.ref.WeakReference;

public class RefDemo {
    public static void main(String[] args) {

        //字符串常量池 弱引用
        String string = new String("rj is very good");
        //弱引用 管理对象
        WeakReference<String> wf = new WeakReference<String>(string);
        System.out.println("before gc run:" + wf.get());
        //断开引用
        string = null;
        //通知回收
        System.gc();
        System.runFinalization();
        //对象被回收
        System.out.println("after gc:" + wf.get());

    }
    public static void testStrong(){
        //字符串常量池 共享（不回收）
        String string = "rj is very good";
        //弱引用 管理对象
        WeakReference<String> wf = new WeakReference<String>(string);
        System.out.println("before gc run:" + wf.get());
        //断开引用
        string = null;
        //通知回收
        System.gc();
        System.runFinalization();
        System.out.println("after gc:" + wf.get());

    }
}
