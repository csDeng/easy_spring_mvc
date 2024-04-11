package mvc.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(requestMethod = RequestMethod.GET)
public @interface GetMapping {

    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}