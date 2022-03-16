package priv.rj.learning.io.pattern;


/**
 * 装饰设计模式
 * 类与类之间的关系
 * 1. 依赖：形参|局部变量
 * 2. 关联：属性
 *         关联强度     聚合：属性 整体与部分 不一致的生命周期 人与手
 *                     组合：属性 整体与部分 一致的生命周期 人与大脑
 * 3. 继承：父子类关系
 * 4. 实现：接口与实现关系
 */
public class DecorationPattern {
    public static void main(String[] args) {
        Voice voice = new Voice();
        voice.say();
        Anplifier anplifier = new Anplifier(voice);
        anplifier.say();
    }
}
