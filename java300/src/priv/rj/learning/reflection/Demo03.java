package priv.rj.learning.reflection;

import priv.rj.learning.reflection.bean.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author rjjerry
 */
public class Demo03 {
    public static void main(String[] args) {
        String path = "priv.rj.learning.reflection.bean.User";
        try {
            Class<?> clazz = Class.forName(path);
//            Class<User> clazz = (Class<User>) Class.forName(path);

            //通过反射API调用构造方法，构造对象
            //调用了User的无参构造方法
            User user = (User)clazz.newInstance();
            System.out.println(user);

            Constructor<User> constructor = (Constructor<User>) clazz.getDeclaredConstructor(int.class, String.class, int.class);
            User u2 = constructor.newInstance(1001, "rr", 15);
            System.out.println(u2.getUname());

            //通过反射API调用普通方法
            User u3 = (User) clazz.newInstance();
            Method method = clazz.getDeclaredMethod("setUname", String.class);
            method.invoke(u3, "rrs");
            System.out.println(u3.getUname());

            //通过反射API操作属性
            User u4 = (User)clazz.newInstance();
            Field field = clazz.getDeclaredField("uname");
            //这个属性不需要做安全检查了，可以直接访问
            field.setAccessible(true);
            field.set(u4, "rrf");
            System.out.println(u4.getUname());
            System.out.println(field.get(u4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
