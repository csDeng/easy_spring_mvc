package com.example.show_bean.a03;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.show_bean.a03")
public class A03Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A03Application.class);
//        context.close();
    }
}
