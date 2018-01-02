package com.yy.crm.security.core.code;


import com.yy.crm.security.core.code.image.ImageCodeGenerator;
import com.yy.crm.security.core.code.sms.DefaultSmsCodeSender;
import com.yy.crm.security.core.code.sms.SmsCodeSender;
import com.yy.crm.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
@Configuration
public class ValidateCodeBeanConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")//如果没有这个bean就用下面配置
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
