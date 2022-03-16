package priv.rj.learning.threads;

/**
 * 静态代理 设计模式
 * 真实角色
 * 代理角色:持有真实角色的引用
 * 二者实现相同的接口
 */
public class StaticProxy {
    public static void main(String[] args) {
        You you = new You();
        WeddingCompany weddingCompany = new WeddingCompany(you);
        weddingCompany.marry();
    }
}
//接口
interface Marry {
    public abstract void marry();
}
//真实角色
class You implements Marry {

    @Override
    public void marry() {
        System.out.println("you and me married");
    }
}

/**
 * 代理
 * 婚庆公司
 * 包含你和他真实角色的引用
 */
class WeddingCompany implements Marry{
    /**
     * 真是角色的引用
     */
    private Marry you;

    /**
     * 空构造器
     */
    public WeddingCompany(){

    }

    /**
     * 代理的方法
     * 对象引用的方法
     * @param you 真实角色的引用
     */
    public WeddingCompany(Marry you) {
        this.you = you;
    }

    public void before(){
        System.out.println("布置");
    }

    public void after() {
        System.out.println("nknknk");
    }

    /**
     * 重写接口
     * 代理接口的方法
     */
    @Override
    public void marry() {
        before();
        you.marry();
        after();
    }


}