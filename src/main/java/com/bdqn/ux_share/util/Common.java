package com.bdqn.ux_share.util;

/**
 * 消息公共类
 */
public class Common<T> {
    private long code;// 状态码
    private String message;// 消息
    private T data;// 数据

    public Common(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> Common success(){
        return new Common(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),true);
    }
    public static<T> Common success(String message){
        return new Common(ResultCode.SUCCESS.getCode(),message,true);
    }
    public static<T> Common success(T data){
        return new Common(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    public static<T> Common failed(){
        return new Common(ResultCode.FAILED_ERROR.getCode(),ResultCode.FAILED_ERROR.getMessage(),false);
    }

    public static<T> Common failed(String message){
        return new Common(ResultCode.FAILED_ERROR.getCode(),message,false);
    }

    public static<T> Common failedParameter(){
        return new Common(ResultCode.FAILED_PARAMETER.getCode(), ResultCode.FAILED_PARAMETER.getMessage(),false);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
