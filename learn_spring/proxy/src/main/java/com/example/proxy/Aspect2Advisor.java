//package org.springframework.aop.framework.autoproxy;
//
//import org.aopalliance.intercept.MethodInterceptor;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ConfigurationClassPostProcessor;
//import org.springframework.context.support.GenericApplicationContext;
//import org.springframework.core.annotation.Order;
//
//import java.util.List;
//
//public class Aspect2Advisor {
//
//
//    public static void main(String[] args) {
//        GenericApplicationContext context = new GenericApplicationContext();
//        context.registerBean("aspect1", Aspect1.class);
//        context.registerBean("config", Config.class);
//        context.registerBean(ConfigurationClassPostProcessor.class);
//        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);
//        context.refresh();
//
//
//
//        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
//
//        // 因为现在 target 类并没有被spring管理，所以第二个参数可以随便填
////        List<Advisor> advisors = creator.findEligibleAdvisors(Target1.class, "target1");
////        for (Advisor advisor : advisors) {
////            System.out.println(advisor);
////        }
//
//
//        Object o1 = creator.wrapIfNecessary(new Target1(), "target1", "target1");
//        System.out.println(o1.getClass());
//        Object o2 = creator.wrapIfNecessary(new Target2(), "target2", "target2");
//        System.out.println(o2.getClass());
//
//
//    }
//
//
//    static class Target1 {
//        public void foo() {
//            System.out.println("target1 foo");
//        }
//    }
//
//    static class Target2 {
//        public void bar() {
//            System.out.println("target2 bar");
//        }
//    }
//
//    /**
//     * 高级切面
//     */
//    @Aspect
//    @Order(1)
//    static class Aspect1 {
//        @Before("execution(* foo())")
//        public void before() {
//            System.out.println("aspect1 before...");
//        }
//
//        @After("execution(* foo())")
//        public void after() {
//            System.out.println("aspect1 after...");
//        }
//    }
//
//    @Configuration
//    static class Config {
//        /**
//         * 低级切面，由一个切点和一个通知组成
//         */
//        @Bean
//        public Advisor advisor3(MethodInterceptor advice3) {
//            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//            pointcut.setExpression("execution(* foo())");
//            DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice3);
//            advisor.setOrder(0);
//            return advisor;
//        }
//
//        @Bean
//        public MethodInterceptor advices() {
//            return invocation -> {
//                System.out.println("advice3 before...");
//                Object result = invocation.proceed();
//                System.out.println("advice3 after...");
//                return result;
//            };
//        }
//    }
//}
