package priv.rj.learning.innerclass;

import priv.rj.learning.innerclass.Outer02.StaticInnerClass;
/**
 *测试静态内部类的详细用法
 *
 * */
public class Demo02 {
    public static void main(String[] args) {
        Outer02.StaticInnerClass osic = new Outer02.StaticInnerClass();
        //import priv.rj.learning.innerclass.Outer02.StaticInnerClass;
        StaticInnerClass osic2 = new StaticInnerClass();
    }
}

class Outer02{
    int c =5;
    static int d = 11;

    void ttt(){
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }

     static class StaticInnerClass{
        int a = 3;
        static int b = 5;
        public void test(){
            Outer02 outer02 = new Outer02();
            System.out.println(d);
            System.out.println(outer02.c);
        }
    }
}