package com.bdqn.ux_share.util;

public enum ResultCode {
    SUCCESS(200,"成功"),
    FAILED(404,"请求路径异常"),
    FAILED_PARAMETER(403,"请求参数异常"),
    FAILED_METHOD(405,"请求方式异常"),
    FAILED_ERROR(500,"后台数据异常");

    private long code;
    private String message;

    private ResultCode(long code, String message){
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
