package com.example.show_bean.a04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

public class A04Application {
    public static void main(String[] args) {
        // GenericApplicationContext 是一个干净的容器
        GenericApplicationContext context = new GenericApplicationContext();

//        // 解析值注入内容
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
//
//        // @Autowired @Value
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
//
//        // @Resource @PostConstruct @PreDestroy
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
//
//        // @ConfigurationProperties  获取环境变量信息
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());


        // 用原始方式注册三个 bean
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);

        context.registerBean("bean4", Bean4.class);

        // 初始化容器。执行 beanFactory 后置处理器，添加 bean 后置处理器，初始化所有单例
        context.refresh();


        System.out.println(context.getBean(Bean4.class));

        // 销毁容器
        context.close();
    }
}