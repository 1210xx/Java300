package priv.rj.learning.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rjjerry
 */

@Target(value = {ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation01 {
    String studentName() default " " ;
    int age() default 0;
    int id() default -1;
    String[] schools() default {"thu","sxt"};
}
