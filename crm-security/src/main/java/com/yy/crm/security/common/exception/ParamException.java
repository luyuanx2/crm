package com.yy.crm.security.common.exception;

import com.yy.crm.common.response.CommonEnum;
import lombok.Getter;

/**
 * 参数校验异常
 */
@Getter
public class ParamException extends RuntimeException {
    private Integer code;

    public ParamException(CommonEnum commonEnum) {
        super(commonEnum.getMessage());
        this.code = commonEnum.getCode();
    }

    public ParamException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public ParamException(String message) {
        super(message);
        this.code = 5000;
    }

}
