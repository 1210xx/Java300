package priv.rj.learning.threads.info;

public class Mythread implements Runnable{
    private boolean flag = true;
    private int num = 0;
    @Override
    public void run() {
        while (flag){
            System.out.println(Thread.currentThread() + "------>" + num++);
        }
    }
    public void stop(){
        this.flag = !this.flag;
    }
}
