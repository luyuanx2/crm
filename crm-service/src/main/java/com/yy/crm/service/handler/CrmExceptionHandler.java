package com.yy.crm.service.handler;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.security.common.exception.CrmException;
import com.yy.crm.security.common.exception.ParamException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by luyuanyuan on 2017/9/27.
 */
@ControllerAdvice
public class CrmExceptionHandler {

    @ExceptionHandler(value = {CrmException.class,ParamException.class})
    @ResponseBody
    public ServerResponse handlerSellException(Exception e){
        if (e instanceof CrmException) {
            return ServerResponse.createByErrorCodeMessage(((CrmException) e).getCode(), e.getMessage());
        }
        if (e instanceof ParamException) {
            return ServerResponse.createByErrorCodeMessage(((ParamException) e).getCode(), e.getMessage());
        }
        return ServerResponse.createByError();
    }
}