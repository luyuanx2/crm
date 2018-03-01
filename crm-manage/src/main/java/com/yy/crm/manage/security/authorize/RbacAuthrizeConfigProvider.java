package com.yy.crm.manage.security.authorize;

import com.yy.crm.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 鲁源源 on 2018/3/1.
 */
@Component
@Order(Integer.MAX_VALUE)
public class RbacAuthrizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        //todo https://github.com/spring-projects/spring-security-oauth/issues/730
        //config
        //        .antMatchers(HttpMethod.GET, "/fonts/**").permitAll()
        //        .antMatchers(HttpMethod.GET,
        //                "/**/*.html",
        //                "/admin/me",
        //                "/resource").authenticated()
        //        .anyRequest()
        //        .access("@rbacService.hasPermission(request, authentication)");
        //return true;
        config.antMatchers("/user/info").authenticated().anyRequest().access("@rbacService.hasPermission(request, authentication)");
        return true;
    }
}
