package com.example.auto_conf;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

public class DbAutoConfApplication {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        StandardEnvironment env = new StandardEnvironment();
        env.getPropertySources().addLast(new SimpleCommandLinePropertySource(
            "--spring.datasource.url=jdbc:mysql://localhost:3306/advanced_spring",
            "--spring.datasource.username=root",
            "--spring.datasource.password=123456"
        ));
        context.setEnvironment(env);
        // 注册常用的后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean("config", Config.class);
        context.refresh();
        for (String name : context.getBeanDefinitionNames()) {
            String resourceDescription = context.getBeanDefinition(name).getResourceDescription();
            if (resourceDescription != null){
                System.out.println(name + " 来源: \n" + resourceDescription);
                System.out.println("-----------");
            }

        }


    }

    @Configuration
    @Import({DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        TransactionAutoConfiguration.class
    })
    static class Config {

    }

}
