package com.example.auto_conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

public class AopAutoConfApplication {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 注册常用的后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean("config", Config.class);
        context.refresh();
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(">>>" + name);
        }

    }

    @Configuration
    @Import({MyImportSelector.class})
    static class Config {

    }

    static class MyImportSelector implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{AopAutoConfiguration.class.getName()};
        }
    }

}
