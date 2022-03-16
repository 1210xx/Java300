package priv.rj.learning.rorm.utils;

import java.lang.reflect.Method;

/**
 * 封装反射常用操作
 *
 * @author rjjerry
 */
public class ReflectUtils {

    /**
     * 调用obj的get方法
     *
     * @param fieldName 属性名字
     * @param obj       对象
     * @return get方法返回值
     */
    public static Object invokeGet(String fieldName, Object obj) {
        //通过反射机制，调用属性对应的get或set方法
        try {
            Class c = obj.getClass();
            Method method = c.getDeclaredMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
            return method.invoke(obj, null);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static void invokeSet(Object obj, String columnName, Object columnValue) {

        try {
            if (columnValue != null) {
                Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName), columnValue.getClass());
                m.invoke(obj, columnValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
