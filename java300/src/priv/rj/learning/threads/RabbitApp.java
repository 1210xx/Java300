package priv.rj.learning.threads;


public class RabbitApp {
    public static void main(String[] args) {
        //创建子类对象
        Rabbit rabbit = new Rabbit();
        Tortoise tortoise = new Tortoise();
        //调用start方法
        rabbit.start();//普通方法的调用
        tortoise.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("跑了" + i + "步");
        }


    }
}
