package priv.rj.learning.threads.syn;
//单例设计模式：确保一个类只有一个对象

public class SyncDemo02 {
    public static void main(String[] args) {
        JvmThread thread1 = new JvmThread(500);
        JvmThread thread2 = new JvmThread(100);
        thread1.start();
        thread2.start();
//        Jvm j1 = Jvm.getInstance();
//        Jvm j2 = Jvm.getInstance();
//        System.out.println(j1);
//        System.out.println(j2);
    }
}

class JvmThread extends Thread {
    private long time;

    public JvmThread(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->创建 " + Jvm.getInstance(time));
    }
}

/**
 * 单例设计模式：确保一个类只有一个对象
 * 懒汉式：double checking
 * 1. 构造器私有化，避免外部直接创建对象
 * 2. 声明一个私有的静态变量
 * 3. 创建一个对外的公共静态方法访问该变量，如果变量没有对象，创建该对象
 */

class Jvm {

    //声明一个私有的静态变量
    private static Jvm instance = null;


    //构造器私有化，避免外部直接创建对象
    private Jvm() {

    }

    public static Jvm getInstance(long time) {
        //提高效率
        if (null == instance) {
            synchronized (Jvm.class) {
                if (null == instance) {
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Jvm();
                }
            }

        }
        return instance;
    }

    //创建一个对外的公共静态方法访问该变量，如果变量没有对象，创建该对象
    public static Jvm getInstance3(long time) {
        //同步块  效率不高
        synchronized (Jvm.class) {
            if (null == instance) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Jvm();
            }
            return instance;
        }
    }

    //同步方法
    public static synchronized Jvm getInstance2(long time) {
        if (null == instance) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Jvm();
        }
        return instance;
    }

    public static Jvm getInstance1(long time) {
        if (null == instance) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Jvm();
        }
        return instance;
    }
}
