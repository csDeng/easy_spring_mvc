package com.example.show_bean.a03;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component(value = "lifeCycleBean")
public class LifeCircleBean {

    LifeCircleBean(){
        System.out.println(">>>>>>>>> 构造器");
    }

    @Autowired
    public void auto(@Value("${JAVA:'hello_home'}") String home) {
        System.out.println(">>>>>>>>>注入"+home);
    }

    @PostConstruct
    public void post() {
        System.out.println(">>>>>>>>> post construct");
    }


    @PreDestroy
    public void prev() {
        System.out.println(">>>>>>>>>> preDestroy");
    }

}
