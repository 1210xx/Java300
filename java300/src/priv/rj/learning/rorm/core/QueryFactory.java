package priv.rj.learning.rorm.core;

/**
 * 创建Query对象的工厂类
 * 负责配置信息创建query对象
 * 工厂模式
 */
public class QueryFactory {
    /**
     * 克隆模式
     */
    private static Query prototypeObj;

    /**
     * 私有化构造器
     */
    private QueryFactory() {
    }

    static {

        try {
            //加载指定的query类
            Class c = Class.forName(DBManger.getConf().getQueryClass());
            prototypeObj = (Query) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Query createQuery() {

        //直接返回
//        return new MysqlQuery();

        //反射调用
//        try {
//            return (Query) c.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
        //克隆模式
        try {
            return (Query) prototypeObj.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
