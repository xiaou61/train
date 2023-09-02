package com.xiaou.exception;

public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum e;


    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }


    public BusinessExceptionEnum getAnEnum() {
        return e;
    }


    public void setAnEnum(BusinessExceptionEnum e) {
        this.e = e;
    }




}
