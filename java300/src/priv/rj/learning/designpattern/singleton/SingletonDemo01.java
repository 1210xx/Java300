package priv.rj.learning.designpattern.singleton;

/**
 * 测试饿汉式单例模式
 * @author rjjerry
 */
public class SingletonDemo01 {
    //类初始化时立即加载对象！
    private static SingletonDemo01 instance = new SingletonDemo01();

    private SingletonDemo01(){

    }

    public static SingletonDemo01 getInstance(){
        return instance;
    }
}
