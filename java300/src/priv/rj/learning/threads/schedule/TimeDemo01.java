package priv.rj.learning.threads.schedule;



import java.util.Timer;
import java.util.TimerTask;

public class TimeDemo01 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("so easy.....");
            }
        }, 10000);
    }
}
