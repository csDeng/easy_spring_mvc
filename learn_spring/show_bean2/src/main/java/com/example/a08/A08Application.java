package com.example.a08;


import com.example.a07.Bean1;
import com.example.a07.Bean2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class A08Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A08Application.class, args);
        E e = context.getBean(E.class);
        log.info("{}", e.getClass());
        log.info("a = {}", e.getA());
        log.info("a = {}", e.getA());
        log.info("a = {}", e.getA());
        context.close();
    }

}

