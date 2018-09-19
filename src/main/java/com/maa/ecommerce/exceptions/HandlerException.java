package com.maa.ecommerce.exceptions;

public class HandlerException extends java.lang.Exception
{

    private final String massage;
    private final int errorCode;

    public HandlerException(int code, String msg){
        this.errorCode = code;
        this.massage = msg;
    }

    public ErrorDetails getErrorDetails(){
        ErrorDetails e = new ErrorDetails();
        e.setCode(errorCode);
        e.setMassage(massage);
        return e;
    }

}
