package priv.rj.learning.threads.status;

public class YieldDemo01 extends Thread{
    public static void main(String[] args) {
        JoinDemo01 yieldDemo01 = new JoinDemo01();
        Thread thread = new Thread(yieldDemo01);//新生
        thread.start();//就绪
        for (int i = 0; i < 1000; i++) {
            if (i % 20 == 0){
                //暂停本线程
                Thread.yield();
            }
            System.out.println("main----->" + i);
        }
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 1000; i++) {
            System.out.println("yield----->" + i);
        }
    }
}
