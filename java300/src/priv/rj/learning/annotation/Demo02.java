package priv.rj.learning.annotation;

/**
 * @author rjjerry
 */

public class Demo02 {
    @MyAnnotation01(age = 19, studentName = "ss", id = 1001, schools = "swpu")
    public void test(){

    }
//    @MyAnnotation02(value = "sss")
    @MyAnnotation02("sss")
    public void test2(){

    }
}
