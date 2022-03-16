package priv.rj.learning.threads.status;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SleepDemo01 {
    public static void main(String[] args) throws InterruptedException {
        countDown();
    }
    public static void countDown() throws InterruptedException {
        Date endTime = new Date(System.currentTimeMillis() + 10 * 1000);
        long end = endTime.getTime();
        while (true) {
            //输出
            System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
            //构建下一秒的时间
            endTime = new Date(endTime.getTime() - 1000);
            //等待一秒
            Thread.sleep(1000);
            //10s以内继续
            if (end - 10000 > endTime.getTime()){
                break;
            }
        }
       }

    public static void countNum() throws InterruptedException {
        int num = 10;
        while (true) {
            System.out.println(num--);
            Thread.sleep(1000);
            if (num < 0)
                break;
            while (true) {
                System.out.println(num--);
                Thread.sleep(1000);
                if (num < 0)
                    break;
            }
        }
    }
}
