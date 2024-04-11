package com.example.show_bean.a06;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;

public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {
    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName" + this.getClass().getSimpleName() + " 名字叫：" + s);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        System.out.println("setBeanName" + this.getClass().getSimpleName() + " applicationContext 容器是：" + applicationContext);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("setBeanName" + this.getClass().getSimpleName() + " InitializingBean");
    }

    @Autowired
    public void set(ApplicationContext applicationContext) {
        System.out.println("setBeanName" + this.getClass().getSimpleName() + " @Autowired 注入的 applicationContext 容器是：" + applicationContext);
    }

    @PostConstruct
    public void init() {
        System.out.println("setBeanName" + this.getClass().getSimpleName() + " @PostConstruct InitializingBean");
    }
}
