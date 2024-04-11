package com.example.proxy;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.transaction.annotation.Transactional;

public class MyPointCutMatch {
    public static void main(String[] args) throws NoSuchMethodException {
        AspectJExpressionPointcut pt1 = new AspectJExpressionPointcut();

        // 判断方法是否匹配
        pt1.setExpression("execution(* bar())");
        System.out.println(pt1.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pt1.matches(T1.class.getMethod("bar"), T1.class));

        AspectJExpressionPointcut pt2 = new AspectJExpressionPointcut();

        // 判断注解是否匹配
        pt2.setExpression("@annotation(org.springframework.transaction.annotation.Transactional)");
        System.out.println(pt2.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pt2.matches(T1.class.getMethod("bar"), T1.class));
    }

    static class T1 {
        @Transactional
        public void foo() {

        }

        public void bar() {

        }
    }

}
