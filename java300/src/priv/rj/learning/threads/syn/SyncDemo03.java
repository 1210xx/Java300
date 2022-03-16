package priv.rj.learning.threads.syn;

public class SyncDemo03 {
    public static void main(String[] args) {
        Object m = new Object();
        Object g = new Object();
        Test t1 = new Test(g, m);
        Test2 t2 = new Test2(g, m);
        Thread proxy = new Thread(t1);
        Thread proxy2 = new Thread(t2);
        proxy.start();
        proxy2.start();
    }

}
class Test implements Runnable{
    Object goods;
    Object money;

    public Test(Object goods, Object money) {
super();
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {
        while (true){
            test();
        }
    }

    public void test(){
        synchronized (goods){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (money){

            }
        }
        System.out.println("give money");
    }
}

class Test2 implements Runnable{
    Object goods ;
    Object money ;

    public Test2(Object goods, Object money) {
        super();
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {
        while (true){
            test();
        }
    }

    public void test(){
        synchronized (money){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (goods){

            }
        }
        System.out.println("give goods");
    }
}
