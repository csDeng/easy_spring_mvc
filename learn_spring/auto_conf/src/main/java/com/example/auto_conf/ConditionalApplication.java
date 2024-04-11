package com.example.auto_conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Map;


public class ConditionalApplication {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean("config", Config.class);
        context.refresh();

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }

    @Configuration
    static class Config {
        @Bean
        @Conditional(MyConditional.class)
        public Bean1 bean1() {
            return new Bean1();
        }
        @Bean
        @Conditional(MyConditional2.class)
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class H {}

    static class MyConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("com.example.auto_conf.ConditionalApplication.H", null);
        }
    }

    static class MyConditional2 implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return !ClassUtils.isPresent("com.example.auto_conf.ConditionalApplication.H", null);
        }
    }
    static class Bean1 { }

    static class Bean2 { }
}
