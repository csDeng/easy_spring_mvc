package com.example.show_bean;


import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

public class AnnotationConfigServletWebContext {

    public static void main(String[] args) {
//        public class AnnotationConfigServletWebServerApplicationContext
//              extends ServletWebServerApplicationContext
//                  implements AnnotationConfigRegistry
        AnnotationConfigServletWebServerApplicationContext context =
            new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        // AnnotationConfigServletWebApplicationContext
        //          extends GenericWebApplicationContext
        //              implements AnnotationConfigRegistry
        // 注意这个类不会启动 tomcat
//        AnnotationConfigServletWebApplicationContext context = new AnnotationConfigServletWebApplicationContext(WebConfig.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(">>>>>>>>>>>name="+name);
        }
//
    }

    static class WebConfig {

        // 内嵌 tomcat
        @Bean
        public ServletWebServerFactory servletWebServerFactory () {
            System.out.println(">>>>>>> tomcat");
            return new TomcatServletWebServerFactory();
        }

        // 路径派发
        @Bean
        public DispatcherServlet dispatcherServlet() {
            System.out.println(">>>>>>>> dispatch");
            return new DispatcherServlet();
        }

        // 注册 dispatch 到 tomcat
        @Bean
        public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet) {
            System.out.println(">>>>>>>>registrationBean");
            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }



        // 使用的是 org.springframework.web.servlet.mvc.Controller
        @Bean("/hello")
        public Controller controller1() {
            System.out.println(">>>>>>>> controller");
            return ((request, response) -> {
                response.getWriter().println("hello");
                return null;
            });
        }
    }
}
