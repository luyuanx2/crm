package com.yy.crm.security.common.exception;

import com.yy.crm.common.response.CommonEnum;
import lombok.Getter;

/**
 * 项目自定义异常
 * @author 鲁源源 on 2018/1/16.
 */
@Getter
public class CrmException extends RuntimeException{
    private Integer code;

    public CrmException(CommonEnum commonEnum) {
        super(commonEnum.getMessage());
        this.code = commonEnum.getCode();
    }

    public CrmException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
