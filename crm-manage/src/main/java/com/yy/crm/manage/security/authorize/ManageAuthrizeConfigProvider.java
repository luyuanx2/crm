package com.yy.crm.manage.security.authorize;

import com.yy.crm.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 鲁源源 on 2018/2/28.
 */
@Component
@Order(Integer.MIN_VALUE)
public class ManageAuthrizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                "/sys/user/regist","/social/signUp","/druid/**","/v2/api-docs",
                "/swagger-resources/configuration/ui", "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html","/redirect"
        ).permitAll();
    }
}
