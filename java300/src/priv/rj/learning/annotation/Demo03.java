package priv.rj.learning.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author rjjerry
 */
public class Demo03 {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("priv.rj.learning.annotation.MyStudent");
            //获得类的所有有效注解
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations){
                System.out.println(annotation);
            }
            //获得类指定的注解
            MyTable mt = (MyTable) clazz.getAnnotation(MyTable.class);
            System.out.println(mt.value());

            //获得类的属性注解
            Field field = clazz.getDeclaredField("stuName");
            MyFiled mf = field.getAnnotation(MyFiled.class);
            System.out.println(mf.columnName() + "---" + mf.type() +"----"+ mf.length());

            //根据获得的表明，字段的信息，拼出DDL语句，然后使用JDBC执行SQL，在数据库中生成相关的表

        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
