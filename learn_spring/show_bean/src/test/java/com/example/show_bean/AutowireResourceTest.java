package com.example.show_bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

public class AutowireResourceTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean 的定义（class，scope，初始化，销毁）

        // 把 Config 类交给spring容器管理
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class)
            .setScope("singleton")
            .getBeanDefinition();

        beanFactory.registerBeanDefinition("config", beanDefinition);
        // 给 BeanFactory 添加一些常用的后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class)
            .values()
            .forEach(i -> i.postProcessBeanFactory(beanFactory));
        beanFactory.getBeansOfType(BeanPostProcessor.class)
            .values()
            .forEach(beanFactory::addBeanPostProcessor);
        System.out.println(beanFactory.getBean(Bean1.class).getInner());

    }

    @Configuration
    static class Config {

        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }
        @Bean
        public Bean4 bean4() {
            return new Bean4();
        }


        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

    }


    static class Bean1 {

        @Autowired
        @Resource(name = "bean4")
        private Inner bean3;

        private Inner getInner() {
            return bean3;
        }


    }

    interface Inner {

    }
    static class Bean3 implements Inner {
            Bean3() {
                System.out.println(">>>>>>>>>.3");
            }
    }
    static class Bean4 implements Inner {
        Bean4() {
            System.out.println(">>>>>>>>>>>>>.4");
        }
    }






}
