package priv.rj.learning.innerclass;


/**
 * 测试方法内部类（局部内部类）
 */
public class Demo04 {
    public static void main(String[] args) {
        Outer04 outer04 = new Outer04();
        outer04.test();

    }
}

class Outer04 {
    public void test() {

        int a = 33;
        int anInt = 3;
        class Inner {
            int b = 53;
            //方法内部类只能定义非静态成员
//            static int c = 3333;
            void tt(){
                System.out.println(b);
                System.out.println(anInt);
                System.out.println(a);
            }
        }

        Inner inner = new Inner();
        inner.tt();
    }

    public void test02(){

    }

}