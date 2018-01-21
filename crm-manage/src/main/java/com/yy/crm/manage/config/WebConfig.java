package com.yy.crm.manage.config;
import com.yy.crm.manage.config.filter.LoginFilter;
import com.yy.crm.manage.config.interceptor.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyuanyuan on 2017/10/20.
 */
//@Configuration
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


    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //    super.addInterceptors(registry);
    //    registry.addInterceptor(httpInterceptor);//.addPathPatterns();
    //}

    @Bean
    public FilterRegistrationBean loginFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        LoginFilter loginFilter = new LoginFilter();
        filterRegistrationBean.setFilter(loginFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/sys");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }
}
