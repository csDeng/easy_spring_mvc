package com.example.auto_conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

public class Auto2ConfApplication {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean("config", Config.class);
        context.refresh();
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(">>>" + name);
        }

        List<String> names = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, null);
        System.out.println("EnableAutoConfiguration>>>>>>>>>");
        names.forEach(System.out::println);
    }

    @Configuration
    @Import({MyImportSelector.class})
    static class Config {

    }

    static class MyImportSelector implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            List<String> names = SpringFactoriesLoader.loadFactoryNames(MyImportSelector.class, null);
            return names.toArray(new String[0]);
        }
    }

    static class Auto {

        @Bean
        public Bean1 bean1() {
            return new Bean1("自动配置");
        }


    }
    static class Auto1 {


        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

    }

    @Data
    @AllArgsConstructor
    static class Bean1 {
        private String name;
    }

    static class Bean2 { }
}
