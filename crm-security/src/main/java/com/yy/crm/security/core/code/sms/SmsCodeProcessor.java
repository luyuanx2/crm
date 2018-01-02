package com.yy.crm.security.core.code.sms;

import com.yy.crm.security.core.code.ValidateCode;
import com.yy.crm.security.core.code.impl.AbstractValidateCodeProcessor;
import com.yy.crm.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 发送短信验证码处理器
 * Created by luyuanyuan on 2017/10/25.
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {

        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
