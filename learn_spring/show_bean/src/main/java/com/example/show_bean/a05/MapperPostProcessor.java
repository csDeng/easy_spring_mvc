package com.example.show_bean.a05;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.lang.NonNull;

public class MapperPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:com/example/show_bean/a05/mapper/**/*.class");
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

            AnnotationBeanNameGenerator nameGenerator = new AnnotationBeanNameGenerator();
            for (Resource resource : resources) {
                MetadataReader reader = factory.getMetadataReader(resource);
                ClassMetadata classMetadata = reader.getClassMetadata();
                if (classMetadata.isInterface() && reader.getAnnotationMetadata().hasAnnotation(Mapper.class.getName())) {
                    // 是接口且有@Mapper
                    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class)
                        .addConstructorArgValue(classMetadata.getClassName())
                        // 按照类型注入 SqlSessionFactory
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .getBeanDefinition();

                    // 生成一个新的 beanDefinitional 生成一个 beanName
                    AbstractBeanDefinition beanDefinition1 = BeanDefinitionBuilder
                        .genericBeanDefinition(classMetadata.getClassName())
                        .getBeanDefinition();
                    String beanName = nameGenerator.generateBeanName(beanDefinition1, beanDefinitionRegistry);
                    beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
                }
            }

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
