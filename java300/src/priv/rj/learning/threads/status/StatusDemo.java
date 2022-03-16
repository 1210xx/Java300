package priv.rj.learning.threads.status;

public class StatusDemo {
    public static void main(String[] args) {
        Study study = new Study();
        new Thread(study).start();

        for (int i = 0; i < 100; i++) {
            if (i == 5) {
                study.stop();
            }
            System.out.println("main-------->" + i);
        }
    }
}
class Study implements Runnable{
private boolean flag = true;
    @Override
    public void run() {
        while (flag){
            System.out.println("study thread");
        }
    }
    //change flag by method
    public void stop(){
        this.flag = false;
    }
}
