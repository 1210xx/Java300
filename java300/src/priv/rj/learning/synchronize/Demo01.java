package priv.rj.learning.synchronize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用 collections 管理同步容器
 * synchronizedList()
 * synchronizedSet()
 * synchronizedMap()
 */
public class Demo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        List<String> synList = Collections.synchronizedList(list);
        System.out.println("线程安全的 list 制作完毕");

    }



}
