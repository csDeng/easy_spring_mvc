package com.example.show_bean.a05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

public class ComponentScanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        try {
            ComponentScan scan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);

            CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();

            // 根据注解生成 bean 名字
            AnnotationBeanNameGenerator annotationBeanNameGenerator = new AnnotationBeanNameGenerator();

            // 获取资源文件
            PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
            if (scan != null) {
                String[] strings = scan.basePackages();
                for (String s : strings) {
                    System.out.println(">>>>> prev->: " + s);
                    //-> classpath*:com/example/show_bean/**/*.class
                    s = "classpath*:" + s.replace(".", "/") + "/**/*.class";
                    System.out.println(">>>>> post->: " + s);
                    Resource[] resources = patternResolver.getResources(s);
                    for (Resource resource : resources) {
                        MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                        String className = metadataReader.getClassMetadata().getClassName();
//                    System.out.println("类名：" + className);
                        boolean b = metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName());
//                    System.out.println("是否加了 @Component: " + b);
                        boolean b1 = metadataReader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName());
//                    System.out.println("是否加了 @Component 派生注解: " + b1);
                        if (b || b1) {
                            // 加了 @Component 以及派生注解的 转换成 bean
                            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                                .genericBeanDefinition(className)
                                .getBeanDefinition();
                            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory defaultListableBeanFactory) {
                                String beanName = annotationBeanNameGenerator.generateBeanName(beanDefinition, defaultListableBeanFactory);
                                defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinition);
                            }
                        }

                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException("注册 bean 失败" + e.getMessage());
        }
    }
}