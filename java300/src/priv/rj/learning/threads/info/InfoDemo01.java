package priv.rj.learning.threads.info;

public class InfoDemo01 {
    public static void main(String[] args) throws InterruptedException {
        Mythread it = new Mythread();

        Thread proxy = new Thread(it, "olti");
        proxy.setName("test");
        System.out.println(proxy.getName());
        System.out.println(Thread.currentThread().getName());
        proxy.start();
        System.out.println("启动后的状态：" + proxy.isAlive());


        Thread.sleep(2000);

        it.stop();
        Thread.sleep(100);
        System.out.println("停止后的状态：" + proxy.isAlive());
    }
}
