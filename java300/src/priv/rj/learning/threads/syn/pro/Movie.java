package priv.rj.learning.threads.syn.pro;

/**
 * wait() 等待释放锁，sleep()不释放锁
 * notify()/notifyall() 唤醒 与synchronized一起使用
 */
public class Movie {
    private String pic;
    //信号灯
    //true produce  wait consumer notify produce
    //false consume
    private boolean flag = true;

    public synchronized void play(String pic){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //produce spend 500ms
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("produce: " + pic);
        //done
        this.pic = pic;
        //notify consumer
        this.notify();
        //produce stop
        this.flag = false;
    }
    public synchronized void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //produce spend 200ms
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //done
        System.out.println("consume: " + pic);
        //notify produce
        this.notifyAll();
        //consume stop
        this.flag = true;
    }
}
