package com.example.factory_bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bean2 {

    @PostConstruct
    public void init() {
        System.out.println("bean2 post init");
    }
}
