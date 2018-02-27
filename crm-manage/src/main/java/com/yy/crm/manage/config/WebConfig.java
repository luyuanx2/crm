package com.yy.crm.manage.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yy.crm.manage.config.bean.LocalDateTime2LongSerializer;
import com.yy.crm.manage.config.interceptor.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by luyuanyuan on 2017/10/20.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private final HttpInterceptor httpInterceptor;

    @Autowired
    public WebConfig(HttpInterceptor httpInterceptor) {
        this.httpInterceptor = httpInterceptor;
    }

    /**
     * api异步消息处理配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

//        configurer.setDefaultTimeout(10000);
//        configurer.registerDeferredResultInterceptors();
//        configurer.setTaskExecutor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(httpInterceptor);//.addPathPatterns();
    }

//    @Bean
//    public FilterRegistrationBean loginFilter(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        LoginFilter loginFilter = new LoginFilter();
//        filterRegistrationBean.setFilter(loginFilter);
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
//        filterRegistrationBean.setUrlPatterns(urls);
//        return filterRegistrationBean;
//    }

    @Bean(name = "mapperObject")
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTime2LongSerializer());
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        om.registerModule(javaTimeModule);
        return om;
    }
}
