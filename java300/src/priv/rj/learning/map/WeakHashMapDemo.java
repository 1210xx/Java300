package priv.rj.learning.map;

import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        WeakHashMap<String, String> map = new WeakHashMap<String,String>();
        //测试数据
        map.put("abc", "a");
        map.put("d", "test");
        map.put("abc", "ssa");
        map.put(new String("rjfff"), "c");
        map.put(new String("dsgf"),"d");
        System.gc();
        System.runFinalization();
        System.out.println(map.size());

    }
}