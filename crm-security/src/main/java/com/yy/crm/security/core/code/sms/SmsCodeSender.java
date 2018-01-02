package com.yy.crm.security.core.code.sms;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
