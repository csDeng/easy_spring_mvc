package com.example.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CglibProxyDemo {
    static class Target {
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] params) {
        Target target = new Target();

        Target proxy = (Target) Enhancer.create(Target.class, (MethodInterceptor) (obj, method, args, methodProxy) -> {
            System.out.println("before...");
            // 用方法反射调用目标
//            Object result = method.invoke(target, args);
            // 内部没使用反射，需要目标（spring 的选择）
//            Object result = methodProxy.invoke(target, args);
// 内部没使用反射，需要代理
            Object result = methodProxy.invokeSuper(obj, args);
            System.out.println("after...");
            return result;
        });

        proxy.foo();
    }
}
