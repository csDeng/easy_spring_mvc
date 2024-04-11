package com.example.show_bean.a05;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class A05Application {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        // 注册后处理器
        context.registerBean(ComponentScanPostProcessor.class);

        context.registerBean(BeanPostProcessor.class);

        context.registerBean(MapperPostProcessor.class);
        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(">>>>>>>> beanName="+name);
        }
    }
}
