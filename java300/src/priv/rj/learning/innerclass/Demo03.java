package priv.rj.learning.innerclass;

/**
 * 成员内部类用法
 */
public class Demo03 {
    public static void main(String[] args) {
        Outer03 out = new Outer03();
        Outer03.InnerClass ic = out.new InnerClass();
        ic.test();
    }
}

class Outer03{
    private int a = 3;
    int b = 10;

    public void ttt(){
        InnerClass innerClass = new InnerClass();
    }
    //成员内部类
    //成员内部类不能有静态成员
    //除非声明为final，并且是编译器可以确定的敞亮表达式
    class InnerClass{
        final static int d = 33;
//        final static Date d2 = new Date();
        private int ed = 3;
        void test(){
            System.out.println(a);
            //创建成员内部类对象时需要有外部类对象的引用
            System.out.println("内部类对象：" + this);
            System.out.println("外部类对象：" + Outer03.this);

        }
    }
}