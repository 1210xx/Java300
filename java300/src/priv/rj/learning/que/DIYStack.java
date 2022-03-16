package priv.rj.learning.que;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用队列实现自定义堆栈
 * 1. pop
 * 2. push
 * 3. peek
 */
public class DIYStack<E> {
    private Deque<E> container = new ArrayDeque<E>();

    private int cap;

    public DIYStack(int cap) {
        super();
        this.cap = cap;
    }

    public boolean push(E e){
        if (container.size() + 1> cap){
            return false;
        }
        return container.offer(e);
    }

    public E pop(){
        return container.pollLast();
    }
    public E peek(){
        return container.peekLast();
    }
    public int size(){
        return this.container.size();
    }

}
