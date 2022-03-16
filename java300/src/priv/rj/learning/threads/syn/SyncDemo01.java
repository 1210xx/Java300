package priv.rj.learning.threads.syn;


public class SyncDemo01 {
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


class Web12306 implements Runnable {
    /**
     * 总的票数
     */
    private int num = 50;
    /**
     * 跳出标记
     */
    private boolean flag = true;

    /**
     * 线程入口
     */
    @Override
    public void run() {
        while (flag) {
        test4();
        }
    }

    /**
     * 锁的测试1
     */
    public void test4(){
        //只能锁引用类型
        //锁定范资源不正确，太小
        synchronized ((Integer)num){
            if (num < 0) {
                flag = false;
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
        }
    }


    /**
     * 锁饮用类型，对象
     */
    public void test3(){
        //只能锁引用类型
        synchronized (this){
            if (num < 0) {
                flag = false;
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
        }
    }

    /**
     * 对整个方法加锁
     */
    public synchronized void test2(){
        if (num < 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
    }


    /**
     * 无锁测试
     */
    public void test1(){
        if (num < 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
    }
}

