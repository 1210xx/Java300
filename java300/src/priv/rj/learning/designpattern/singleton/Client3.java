package priv.rj.learning.designpattern.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 测试多线程环境下五种创建单例模式的效率
 * 多线程测试
 * @author rjjerry
 */
public class Client3 {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        int threadNum = 10;

        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        //Object o = SingletonDemo04.getInstance();
                        Object o = SingletonDemo05.INSTANCE;
                    }
                    countDownLatch.countDown();
                }
            }).start();

        }
        //main线程阻塞，知道计数器为0，才会继续往下执行
        countDownLatch.await();

        long end = System.currentTimeMillis();

        System.out.println("总耗时：" + (end - start));
    }
}
