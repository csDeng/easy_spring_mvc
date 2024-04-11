package com.example.proxy;

public class ProxyTest {

    public static void main(String[] args) {
        Target target = new Target();

        Proxy proxy = new Proxy();
        proxy.setMethodInterceptor((obj, method, args1, methodProxy) -> {
            System.out.println("before----");
//            return method.invoke(target, args1);
//            return methodProxy.invoke(target, args1);  // 内部无反射调用 结合目标对象使用
            return methodProxy.invokeSuper(obj, args1); // 内部无反射调用， 结合代理对象使用
        });

        proxy.save();
        proxy.save(1);
        proxy.save(2L);
    }
}
