package com.maa.ecommerce.exceptions;

import org.springframework.http.HttpStatus;


public class NotFoundException extends Exception {

    private String massage;
    private int errorCode = HttpStatus.NOT_FOUND.value();

    public NotFoundException(int code, String msg){
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
