package com.example.proxy;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class Jdk1 {
    interface Foo {
        void foo();
    }

    static final class Target implements Foo {
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] args) throws IOException {
        // 原始对象
        Target target = new Target();

        // 用来加载在运行期间动态生成的字节码
        ClassLoader classLoader = Jdk1.class.getClassLoader();
        Foo proxy = (Foo) Proxy.newProxyInstance(classLoader, new Class[]{Foo.class}, (p, method, params) -> {
            System.out.println("before...");
            // 目标.方法(参数) --> 方法.invoke(目标, 参数)
            Object result = method.invoke(target, params);
            System.out.println("after...");
            // 也返回目标方法执行的结果
            return result;
        });

        // 打印代理类的全限定类名
        System.out.println(proxy.getClass());

        proxy.foo();

        // 只要不在控制台上输入并回车，程序就不会终端
        System.in.read();
    }


}


