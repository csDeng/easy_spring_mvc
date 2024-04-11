package com.example.show_bean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

public class ApplicationContextTest {

    public static void main(String[] args) {
//        testClassPathXmlApplicationContext();
//        testFileSystemXmlApplicationContext();


        // xml 读取的原理
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        System.out.println(">>>>>>>> 读取钱前");
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//        System.out.println(">>>>>>> 读取后");
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        int i = reader.loadBeanDefinitions(new ClassPathResource("b01.xml"));
////        int i = reader.loadBeanDefinitions(new FileSystemResource("//Users/csjerry/project/java/easy_spring_mvc/learn_spring/show_bean/src/test/resources/b01.xml"));
//        System.out.println(">>>>>>>>>> 读取" + i + "bean");
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
        // == 配置类
//        testAnnotationConfigApplicationContext();
        testAnnotationConfigServletWebApplication();
    }




    /**
     * 最为经典 基于 classpath 下的xml配置文件来创建
     */
    private static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("b01.xml");
        System.out.println(context.getBean(Bean2.class).getBean1());
    }


    /**
     * 基于磁盘路径的 xml 配置文件来创建
     */
    private static void testFileSystemXmlApplicationContext() {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("//Users/csjerry/project/java/easy_spring_mvc/learn_spring/show_bean/src/test/resources/b01.xml");
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     * java 配置类来创建
     */
    private static void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(context.getBean(Bean2.class).getBean1());
    }


    private static void testAnnotationConfigServletWebApplication() {
        AnnotationConfigServletWebApplicationContext context = new AnnotationConfigServletWebApplicationContext(WebConfig.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(">>>>>>>>>>>name="+name);
        }
    }

    @Configuration
    public static class WebConfig {

        // 内嵌 tomcat
        @Bean
        public ServletWebServerFactory servletWebServerFactory () {
            return new TomcatServletWebServerFactory();
        }

        // 路径派发
        @Bean
        public DispatcherServlet dispatcherServlet() {
            return new DispatcherServlet();
        }

        // 注册 dispatch 到 tomcat
        @Bean
        public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
                return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }

        // 使用的是 org.springframework.web.servlet.mvc.Controller
        @Bean("/hello")
        public Controller controller() {
            System.out.println(">>>>>>>> controller");
            return ((request, response) -> {
                response.getWriter().println("hello");
                return null;
            });
        }
    }

//    @Configuration
    public static class Config {

        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2(Bean1 bean1) {
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return bean2;
        }
    }

    public static class Bean1 {
        Bean1() {
            System.out.println(">>>>>>>>>> 1");
        }
    }

    public static class Bean2 {
        private Bean1 bean1;

        public Bean2() {
            System.out.println(">>>>>>>>>>>> 2.");
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }

        public Bean1 getBean1() {
            return bean1;
        }
    }


}
