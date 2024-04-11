package com.example.factory_bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bean1PostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("bean1".equals(beanName) && bean instanceof Bean1) {
            log.info("before [{}] init", beanName);
        }
        return bean;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("bean1".equals(beanName) && bean instanceof Bean1) {
            log.info("after [{}] init", beanName);
        }
        return bean;
    }
}
