package priv.rj.learning.que;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用队列模拟银行存款业务
 */
public class Demo01 {
    public static void main(String[] args) {
        Queue<Request> queue = new ArrayDeque<Request>();
        for (int i = 0; i < 10; i++) {
            final int num = i;
            queue.offer(new Request() {
                @Override
                public void deposit() {
                    System.out.println("第" + num + "个人，办理存款业务，存款额度为：" +(Math.random() * 10000));
                }
            });
        }
        dealWith(queue);
    }
    public static void dealWith(Queue<Request> que){
        Request request = null;
        while ((request = que.poll()) != null){
            request.deposit();
        }
    }
}

interface Request{
    //存款
    void deposit();
}