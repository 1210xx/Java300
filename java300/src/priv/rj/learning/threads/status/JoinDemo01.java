package priv.rj.learning.threads.status;

public class JoinDemo01 extends Thread {
    public static void main(String[] args) throws InterruptedException {
        JoinDemo01 joinDemo01 = new JoinDemo01();
        Thread thread = new Thread(joinDemo01);//新生
        thread.start();//就绪

        //cpu调度运行
        for (int i = 0; i < 100; i++) {
            if (50 == i){
                thread.join();//main阻塞
            }
            System.out.println("main ...." + i);
        }

    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100 ; i++) {
            System.out.println("join...." + i);
        }
    }
}
