package com.yy.crm.security.core.authorize.impl;

import com.yy.crm.security.core.authorize.AuthorizeConfigProvider;
import com.yy.crm.security.core.properties.SecurityConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 鲁源源 on 2018/1/28.
 */
@Component
@Order(Integer.MIN_VALUE)
public class MyAuthrizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/**"
        ).permitAll();

        return false;
    }
}
