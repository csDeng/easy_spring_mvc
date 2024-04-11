package com.example.show_bean.a06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class MyConfig2 implements InitializingBean, ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        log.info("注入 ApplicationContext");
    }


    @Bean
    public BeanFactoryPostProcessor processor1() {
        return processor -> log.info("执行 processor1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化");
    }
}

