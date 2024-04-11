package com.example.a08;

import lombok.Getter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
//@Getter
public class E {

//    @Getter
//    @Lazy
//    @Resource
//    private A a;

//    @Resource
//    private ObjectFactory<A> f;
//
//    public A getA() {
//        return f.getObject();
//    }


    @Resource
    private ApplicationContext applicationContext;

    public A getA() {
        return applicationContext.getBean(A.class);
    }
}
