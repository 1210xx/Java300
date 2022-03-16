package priv.rj.learning.threads;

public class Web12306 implements Runnable {
    private int num = 50;

    @Override
    public void run() {
        while (true){
            if (num < 0) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
        }
    }

    public static void main(String[] args) {
        //真实角色
        Web12306 web12306 = new Web12306();
        //代理
        Thread t1 = new Thread(web12306, "1");
        Thread t2 = new Thread(web12306, "2");
        Thread t3 = new Thread(web12306, "3");

        t1.start();
        t2.start();
        t3.start();
    }
}
