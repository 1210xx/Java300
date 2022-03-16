package priv.rj.learning.loadclass;

public class Demo01 {
    static {
        System.out.println("Demo01的初始化块");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Demo01的main方法");
//        A a = new A();
//        System.out.println(A.width);
//        A a2 = new A();


        //主动引用
//        new A();
//        System.out.println(A.width);
//        Class.forName("priv.rj.learning.loadclass.Demo01");

        //被动引用

        //System.out.println(A.MAX);

        A[] acs = new A[10];
        System.out.println(B.width);
    }
}

class B extends A{
    static {
        System.out.println("静态初始化B");
    }
}


class A extends A_Father{
    public static int width =100;
    public static final int MAX = 111;
    static {
        System.out.println("静态初始化类A");
        width = 300;
    }
    public A (){
        System.out.println("创建A对象");
    }
}


class A_Father {
    static {
        System.out.println("静态初始化A的父类");
    }
}