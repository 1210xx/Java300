package priv.rj.learning.designpattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 测试懒汉式单例模式（如果防止反射和反序列化漏洞）
 */
public class SingletonDemo06 implements Serializable {

    private static SingletonDemo06 instance;

    //防止反射破解
    private SingletonDemo06(){
        if (instance != null){
            throw new RuntimeException();
        }
    }

    public static synchronized SingletonDemo06 getInstance(){
        if (null == instance){
            instance = new SingletonDemo06();
        }
        return instance;
    }

    //反序列式，如果定义了readResolve方法，则直接返回指定的对象，不需要单独返回新的对象
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
