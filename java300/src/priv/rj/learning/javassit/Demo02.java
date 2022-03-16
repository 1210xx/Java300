package priv.rj.learning.javassit;

import javassist.*;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * javassit的API
 */

public class Demo02 {
    //处理类的基本用法
    public static void test01() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("priv.rj.learning.javassit.Emp");

        byte[] bytes = cc.toBytecode();
        System.out.println(Arrays.toString(bytes));

        System.out.println(cc.getName());
        System.out.println(cc.getSimpleName());
        System.out.println(cc.getSuperclass());
        System.out.println(cc.getInterfaces());

    }
    //处理方法的基本用法

    /**
     * 测试产生新的方法
     *
     * @throws Exception
     */

    public static void test02() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("priv.rj.learning.javassit.Emp");

        //CtMethod method = CtNewMethod.make("public int add(int a , int b){return a+b;}", cc);

        CtClass[] parameters;
        CtMethod m = new CtMethod(CtClass.intType, "add", new CtClass[]{CtClass.intType, CtClass.intType}, cc);

        m.setModifiers(Modifier.PUBLIC);
        m.setBody("{System.out.println(\"add method \"); return $1+$2;}");
        cc.addMethod(m);
        //通过反射调用新生成的方法
        Class clazz = cc.toClass();
        //通过Emp无参构造器创建对象
        Object object = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("add", int.class, int.class);
        Object result = method.invoke(object, 200, 300);
        System.out.println(result);


    }

    /**
     * 修改方法
     *
     * @throws Exception
     */
    public static void test03() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("priv.rj.learning.javassit.Emp");

        CtMethod cm = cc.getDeclaredMethod("sayHello", new CtClass[]{CtClass.intType});
        cm.insertBefore("System.out.println($1);System.out.println(\"start\");");
        cm.insertAt(35, "int b=13;System.out.println(\"b = \" + b);");
        cm.insertAfter("System.out.println(\"end\");");
        //通过反射调用新生成的方法
        Class clazz = cc.toClass();
        //通过Emp无参构造器创建对象
        Object object = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sayHello", int.class);
        Object result = method.invoke(object, 200);
        System.out.println(result);
    }

    /**
     * 增加方法
     *
     * @throws Exception
     */
    public static void test04() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("priv.rj.learning.javassit.Emp");

        //CtField f1 = CtField.make("private int empno;" , cc);
        CtField f1 = new CtField(CtClass.intType, "salary", cc);
        f1.setModifiers(Modifier.PUBLIC);
        cc.addField(f1);

        //cc.getDeclaredField("ename");

        //增加相应的setget方法
        cc.addMethod(CtNewMethod.getter("getSalary", f1));
        cc.addMethod(CtNewMethod.setter("setSalary", f1));
    }

    /**
     * 构造方法操作
     *
     * @throws Exception
     */
    public static void test05() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("priv.rj.learning.javassit.Emp");

        CtConstructor[] cs = cc.getConstructors();
        for (CtConstructor s : cs) {
            System.out.println(s.getLongName());
        }
    }

    /**
     * 获取注解
     * @throws Exception
     */
    public static void test06()throws Exception{
        CtClass cc = ClassPool.getDefault().get("priv.rj.learning.javassit.Emp");
        Object[] all = cc.getAnnotations();
        Auther auther = (Auther)all[0];
        System.out.println("name " + auther.name() + "---->" + "year: " + auther.year());
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        test06();
    }
}
