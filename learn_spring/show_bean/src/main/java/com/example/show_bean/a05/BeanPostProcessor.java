package com.example.show_bean.a05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

public class BeanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
            MetadataReader reader = cachingMetadataReaderFactory.getMetadataReader(new ClassPathResource("com/example/show_bean/a05/Config.class"));
            Set<MethodMetadata> annotatedMethods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
            for (MethodMetadata method : annotatedMethods) {
                String initMethod = method.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();

                BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
                definitionBuilder.setFactoryMethodOnBean(method.getMethodName(), "config");
                // 设置注入模式
                definitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
                if(StringUtils.hasLength(initMethod)) {
                    definitionBuilder.setInitMethodName(initMethod);
                }

                AbstractBeanDefinition bd = definitionBuilder.getBeanDefinition();
                if(configurableListableBeanFactory instanceof DefaultListableBeanFactory defaultListableBeanFactory) {
                    defaultListableBeanFactory.registerBeanDefinition(method.getMethodName(), bd);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
