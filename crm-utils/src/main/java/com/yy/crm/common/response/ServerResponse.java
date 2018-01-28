package com.yy.crm.common.response;

import java.io.Serializable;

/**
 * Created by 鲁源源 on 2017/6/7.
 */
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable{

    private int code;
    private String message;
    private T data;

    public ServerResponse(int code) {
        this.code = code;
    }

    public ServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServerResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ServerResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    //@JsonIgnore
    public boolean isSuccess(){
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String message){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String message,T data){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message,data);
    }


    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMessage());
    }

    public static <T> ServerResponse<T> createByEnum(CommonEnum commonEnum){
        return new ServerResponse<>(commonEnum.getCode(),commonEnum.getMessage());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<>(errorCode,errorMessage);
    }

    public static <T> ServerResponse<T> createByEnumData(CommonEnum commonEnum, T data) {
        return new ServerResponse<>(commonEnum.getCode(),commonEnum.getMessage(),data);
    }
}
