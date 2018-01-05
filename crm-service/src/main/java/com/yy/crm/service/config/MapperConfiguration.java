package com.yy.crm.service.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * mybatis mapper 扫描配置类
 * @author luyuanyuan on 2018/1/4.
 */
@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MapperConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    private String basePackage;

    private String mappers;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment){

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(basePackage);
        Properties properties = new Properties();
        properties.put("mappers",mappers);
        properties.put("not-empty","false");
        properties.put("identity","MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, null);
        this.basePackage = propertyResolver.getProperty("mapper.basepackage");
        this.mappers = propertyResolver.getProperty("mapper.mappers");
    }
}