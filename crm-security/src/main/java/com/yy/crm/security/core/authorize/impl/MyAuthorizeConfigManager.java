package com.yy.crm.security.core.authorize.impl;

import com.yy.crm.security.core.authorize.AuthorizeConfigManager;
import com.yy.crm.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 鲁源源 on 2018/1/28.
 */
@Component
public class MyAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        authorizeConfigProviders.forEach(authorizeConfigProvider -> authorizeConfigProvider.config(config));
        config.anyRequest().authenticated();
    }
}
