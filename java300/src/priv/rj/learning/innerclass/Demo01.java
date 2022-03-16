package priv.rj.learning.innerclass;

/**
 * 测试内部类定义
 */
public class Demo01 {

    //静态内部类
    private static class StaticNestedClass{

    }
    //普通内部类（成员内部类）
    private class FiledInnerClass{

    }

    void sayHello(){

        //方法内部类（局部内部类）
        class LoadClass{

        }

        //匿名内部类
        //定义了匿名内内部类的类体
        //创建了内部类的一个实例
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
    }
}
