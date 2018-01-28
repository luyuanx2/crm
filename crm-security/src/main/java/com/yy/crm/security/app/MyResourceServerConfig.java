package com.yy.crm.security.app;

import com.yy.crm.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.yy.crm.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yy.crm.security.core.authorize.AuthorizeConfigManager;
import com.yy.crm.security.core.code.ValidateCodeSecurityConfig;
import com.yy.crm.security.core.properties.SecurityConstants;
import com.yy.crm.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.servlet.Filter;


/**
 * 资源服务器
 * Created by 鲁源源 on 2017/11/6.
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private Filter loginFilter;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(loginFilter, FilterSecurityInterceptor.class)
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
        http//.apply(validateCodeSecurityConfig)
                //.and()
                //配置自定义手机登录过滤器相关
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(mySocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                //.authorizeRequests()
                //.antMatchers(
                //        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                //        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                //        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                //        "/sys/user/regist","/social/signUp","/druid/**","/v2/api-docs",
                //        "/swagger-resources/configuration/ui", "/swagger-resources",
                //        "/swagger-resources/configuration/security",
                //        "/swagger-ui.html","/redirect"
                //)
                //.permitAll()
                //.anyRequest()
                //.authenticated()
                //.and()
                .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }
}
