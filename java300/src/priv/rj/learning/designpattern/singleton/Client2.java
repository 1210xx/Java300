package priv.rj.learning.designpattern.singleton;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * 测试反射和反序列化破解
 */
public class Client2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {


        SingletonDemo06 s1 = SingletonDemo06.getInstance();
        SingletonDemo06 s2 = SingletonDemo06.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        //通过反射的方式直接调用私有构造器
//        Class<SingletonDemo06> clazz = (Class<SingletonDemo06>) Class.forName("priv.rj.learning.designpattern.singleton.SingletonDemo06");
//
//        Constructor<SingletonDemo06> c = clazz.getDeclaredConstructor(null);
//
//        c.setAccessible(true);
//
//        SingletonDemo06 s3 = c.newInstance();
//        SingletonDemo06 s4 = c.newInstance();
//
//        System.out.println(s3);
//        System.out.println(s4);

        //通过反序列化构造多个对象
        //序列化
        FileOutputStream fos = new FileOutputStream("/Users/rainjaneJerry/Downloads/java/myjava/a.txt");
        ObjectOutputStream oos =new ObjectOutputStream(fos);
        oos.writeObject(s1);
        oos.close();
        fos.close();

        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/rainjaneJerry/Downloads/java/myjava/a.txt"));
        SingletonDemo06 s3 = (SingletonDemo06)ois.readObject();
        System.out.println(s3);
    }
}
