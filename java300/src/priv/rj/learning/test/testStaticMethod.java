package priv.rj.learning.test;

public class testStaticMethod {
    public static void main(String[] args) {
        test01();
    }

    public static void test01(){
        System.out.println("Static method!");
        testStaticMethod testStaticMethod = new testStaticMethod();
        testStaticMethod.notStaticM();
    }

    public void notStaticM(){
        System.out.println("not static method");
    }
}
