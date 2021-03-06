package com.yy.crm.common.response;


import lombok.Getter;

/**
 * 公共响应枚举
 * Created by 鲁源源 on 2017/6/7.
 */
@Getter
public enum ResponseCode implements CommonEnum{
    SUCCESS(2000,"SUCCESS"),
    ERROR(5000,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    UNAUTHORIZED(4001,"未认证"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
