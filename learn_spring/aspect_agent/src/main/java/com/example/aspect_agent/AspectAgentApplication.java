package com.example.aspect_agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AspectAgentApplication {

    private static final Logger log = LoggerFactory.getLogger(AspectAgentApplication.class);

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(AspectAgentApplication.class, args);
//        MyService service = context.getBean(MyService.class);
//
//        log.info("service class: {}", service.getClass());
//        service.foo();

        new MyService().foo();

    }

}
