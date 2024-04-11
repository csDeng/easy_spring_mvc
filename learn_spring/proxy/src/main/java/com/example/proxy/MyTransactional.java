package com.example.proxy;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

public class MyTransactional {
    public static void main(String[] args) throws NoSuchMethodException {

        StaticMethodMatcherPointcut pt3 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 检查方法上是否添加了 @Transactional 注解
                MergedAnnotations annotations = MergedAnnotations.from(method);
                if (annotations.isPresent(Transactional.class)) {
                    return true;
                }
                // 检查类上或所实现的接口是否添加了 @Transactional 注解
                annotations = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                return annotations.isPresent(Transactional.class);
            }
        };

        System.out.println(pt3.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pt3.matches(T1.class.getMethod("bar"), T1.class));
        System.out.println(pt3.matches(T2.class.getMethod("foo"), T2.class));
        System.out.println(pt3.matches(T3.class.getMethod("foo"), T3.class));
    }

    static class T1 {
        @Transactional
        public void foo() {

        }

        public void bar() {

        }
    }

    @Transactional
    static class T2 {
        public void foo() {

        }
    }

    @Transactional
    interface I3 {
        void foo();
    }

    static class T3 implements I3 {
        @Override
        public void foo() {

        }
    }
}
