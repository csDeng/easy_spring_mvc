package com.example.autowired;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
public class Autowired3Application {

        public static void main(String[] args) throws NoSuchFieldException {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Autowired3Application.class);
            DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
            context.register(Target1.class);
            context.register(Target2.class);
            testPrimary(beanFactory);
            testDefault(beanFactory);
        }

        @SneakyThrows
        private static void testDefault(DefaultListableBeanFactory beanFactory) {
            DependencyDescriptor dd = new DependencyDescriptor(Target2.class.getDeclaredField("service3"), false);
            Class<?> type = dd.getDependencyType();
            for (String name : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, type)) {
                if (name.equals(dd.getDependencyName())) {
                    System.out.println("default: " + name);
                }
            }
            Target2 bean = beanFactory.getBean(Target2.class);
            System.out.println("testDefault >>>>>>" + bean.getService3());

        }

        @SneakyThrows
        private static void testPrimary(DefaultListableBeanFactory beanFactory) {
            DependencyDescriptor dd = new DependencyDescriptor(Target1.class.getDeclaredField("service"), false);
            Class<?> type = dd.getDependencyType();
            for (String name : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, type)) {
                if (beanFactory.getMergedBeanDefinition(name).isPrimary()) {
                    System.out.println("primary: " + name);
                }
            }
            Target1 bean = beanFactory.getBean(Target1.class);
            System.out.println("testPrimary >>>>> " + bean.getService());
        }

        @Data
        static class Target1 {
            @Autowired
            @Qualifier("service2")
            private Service service;
        }

        @Data
        static class Target2 {
            @Autowired
            private Service service3;
        }

        interface Service {
        }

        @Component("service1")
        static class Service1 implements Service {
        }

        @Primary
        @Component("service2")
        static class Service2 implements Service {
        }

        @Component("service3")
        static class Service3 implements Service {
        }
    }
