package priv.rj.learning.designpattern.singleton;

/**
 * 测试静态内部类式单例
 * 线程安全
 * 调用效率高
 * 延时加载
 * @author rjjerry
 */
public class SingletonDemo04 {
    private SingletonDemo04(){

    }
    private static class SingletonHolder{
        private static final SingletonDemo04 SINGLETON_INSTANCE = new SingletonDemo04();
    }
    public static final SingletonDemo04 getInstance(){
        return SingletonHolder.SINGLETON_INSTANCE;
    }
}
