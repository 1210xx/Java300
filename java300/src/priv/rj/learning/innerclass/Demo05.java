package priv.rj.learning.innerclass;

/**
 * 匿名内部类
 */
public class Demo05 {
    public static void main(String[] args) {
        Outer05 outer05 = new Outer05();
        outer05.test();
    }
}

class Outer05{
    public void test02(Car car){
        car.run();
    }
    public void test(){
        //接口式
        //匿名内部类由于本内部类定义在方法内 同时也是方法内部类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        //匿名内部类，继承式
        Car car = new Car(){
            @Override
            public void run(){
                System.out.println("子类的跑船");
            }
        };
        car.run();

        test02(new Car(){
            @Override
            public void run(){
                System.out.println("参数式匿名内部类再跑");
            }
        });
    }
}

class Car{
    public void run(){

    }
}