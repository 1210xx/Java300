package priv.rj.learning.designpattern.singleton;

/**
 * 懒汉式
 */
public class SingletonDemo02 {

    private static SingletonDemo02 singletonDemo02 ;

    private SingletonDemo02(){}

    public static synchronized SingletonDemo02 getInstance(){
        if (null == singletonDemo02){
            singletonDemo02 = new SingletonDemo02();
        }
        return singletonDemo02;
    }
}
