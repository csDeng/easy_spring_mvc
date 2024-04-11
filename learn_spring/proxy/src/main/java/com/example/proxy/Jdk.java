package com.example.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Jdk {

    interface Foo {
        Object foo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

        Object bar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    }



    static abstract class InvokeHandler {
        abstract Object invoke(Method method, Object[] params) throws InvocationTargetException, IllegalAccessException;
    }

    // 代理类
    static class $Proxy0 implements Foo {

        private final InvokeHandler invokeHandler;

        private final Map<String, Method> cache = new HashMap<>();

        $Proxy0(InvokeHandler invokeHandler) {
            this.invokeHandler = invokeHandler;
        }

        @Override
        public Object foo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            // 1. 功能增强
            System.out.println("before");
            // 2. 调用目标
            Method foo = cache.getOrDefault("foo", null);
            if(foo == null) {
                foo = Foo.class.getMethod("foo");
                System.out.println(">>>>>> 新创建方法");
                cache.put("foo", foo);
            }

            return invokeHandler.invoke(foo, new Object[0]);
        }

        @Override
        public Object bar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            // 1. 功能增强
            System.out.println("before");
            // 2. 调用目标
            Method bar = cache.getOrDefault("bar", null);
            if(bar == null) {
                bar = Foo.class.getMethod("foo");
                System.out.println(">>>>>> 新创建方法");
                cache.put("bar", bar);
            }
            return invokeHandler.invoke(bar, new Object[0]);
        }
    }

    static class Target implements Foo {
        @Override
        public Integer foo() {
            System.out.println("target foo");
            return 1;
        }

        @Override
        public String  bar() {
            System.out.println("target bar");
            return "hello";
        }
    }


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Foo f = new $Proxy0(new InvokeHandler() {
            @Override
            Object invoke(Method method, Object[] params) throws InvocationTargetException, IllegalAccessException {
                // 传入代理对象
                return method.invoke(new Target(), params);
            }

        });
        System.out.println(f.foo());
        System.out.println(f.bar());
        System.out.println(f.foo());
        System.out.println(f.bar());
    }
}
