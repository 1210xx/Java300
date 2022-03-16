package priv.rj.learning.threads.info;

/**
 * 优先级
 * MAX_PRIORITY
 * NORM_PRIORITY
 * MIN_PRIORITY
 */
public class ThreadPriorityDemo {
    public static void main(String[] args) throws InterruptedException {
        Mythread it = new Mythread();
        Thread t1 = new Thread(it, "olti1");
        Mythread it2 = new Mythread();
        Thread t2 = new Thread(it2, "olti2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.setPriority(Thread.MAX_PRIORITY);
        t2.start();

        Thread.sleep(10);

        it.stop();
        it2.stop();
    }

}
