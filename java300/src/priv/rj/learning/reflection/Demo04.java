package priv.rj.learning.reflection;

import priv.rj.learning.reflection.bean.User;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 通过反射读取范型
 * @author rjjerry
 */
public class Demo04 {
    public void test01(Map<String, User> map, List<User> list){
        System.out.println("demo04.test01");
    }

    public Map<Integer, User> test02(){
        System.out.println("Demo04.test02");
        return null;
    }

    public static void main(String[] args) {
        try {
            //获取指定方法的参数范型信息
            Method method = Demo04.class.getDeclaredMethod("test01", Map.class, List.class);
            Type[] t = method.getGenericParameterTypes();
            for(Type paramType : t){
                System.out.println("# " + paramType);
                if (paramType instanceof ParameterizedType){
                    Type[] genericTypes = ((ParameterizedType)paramType).getActualTypeArguments();
                    for (Type genericType : genericTypes){
                        System.out.println("范型类型： " + genericType);
                    }
                }
            }
            //获取指定方法的返回值范型信息
            Method m2 = Demo04.class.getDeclaredMethod("test02");
            Type returnType = m2.getGenericReturnType();
            if (returnType instanceof ParameterizedType){
                Type[] genericTypes = ((ParameterizedType) returnType).getActualTypeArguments();
                for (Type genericType : genericTypes){
                    System.out.println("返回值，范型类型：" + genericType);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
