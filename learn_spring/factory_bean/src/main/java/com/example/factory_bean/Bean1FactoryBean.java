package com.example.factory_bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component("bean1")
public class Bean1FactoryBean implements FactoryBean<Bean1> {
    @Override
    public Bean1 getObject() throws Exception {
        Bean1 bean1 = new Bean1();
        log.info("create bean: {}", bean1);
        return bean1;
    }

    @Override
    public Class<?> getObjectType() {
        return Bean1.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
