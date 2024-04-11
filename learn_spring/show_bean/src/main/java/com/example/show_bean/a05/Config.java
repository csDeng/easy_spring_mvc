package com.example.show_bean.a05;


import com.alibaba.druid.pool.DruidDataSource;
import com.example.show_bean.a04.Bean1;
import com.example.show_bean.a05.mapper.Mapper1;
import com.example.show_bean.a05.mapper.Mapper2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@ComponentScan(basePackages = "com.example.show_bean.a05.component")
public class Config {

    public Config() {
        System.out.println(">>>>>>>> config init");
    }

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/advanced_spring");
        dataSource.setName("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    public MapperFactoryBean<Mapper1> mapper1(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<Mapper1> factoryBean = new MapperFactoryBean<>(Mapper1.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }
    @Bean
    public MapperFactoryBean<Mapper2> mapper2(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<Mapper2> factoryBean = new MapperFactoryBean<>(Mapper2.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }


}
