package mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {


    // 具体路径
    String value() default "";

    // 具体请求
    RequestMethod[] requestMethod() default {};
}
