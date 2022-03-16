package priv.rj.learning.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author rjjerry
 */
public class Demo02 {
    public static void main(String[] args) {
        String path = "priv.rj.learning.reflection.bean.User";
        try {
            Class<?> clazz = Class.forName(path);
            //获取类的名字
            //获得包名+类名
            System.out.println(clazz.getName());
            //获得类名
            System.out.println(clazz.getSimpleName());
            //获得属性信息
            //只能获得public的field
//          Field[] fields = clazz.getFields();
            //获得所以的field
            Field[] fields = clazz.getDeclaredFields();
            Field field = clazz.getDeclaredField("uname");
            System.out.println(fields.length);
            for (Field f : fields){
                System.out.println("属性： " + f);
            }
            //获得方法信息
            Method[] methods = clazz.getDeclaredMethods();
            Method method = clazz.getDeclaredMethod("getUname", null);
            //如果刚发有参，则必须传递参数类型对应的class对象
            Method method1 = clazz.getDeclaredMethod("setUname", String.class);
            for (Method m : methods){
                System.out.println("方法： " + m);
            }
            //获得构造器信息
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for (Constructor constructor : constructors){
                System.out.println("构造器： " + constructor);
            }

            Constructor constructor = clazz.getDeclaredConstructor(null);
            System.out.println("无参构造器：" + constructor);
            Class[] parameterTypes;
            Constructor constructor1 = clazz.getConstructor(int.class, String.class, int.class);
            System.out.println("有参构造器" + constructor1);

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
