package com.maa.ecommerce.exceptions;

import org.springframework.http.HttpStatus;


public class NotFoundException extends Exception {

    int errorCode = HttpStatus.NOT_FOUND.value();

    public ErrorDetails getErrorDetails(String msg){
        ErrorDetails e = new ErrorDetails();
        e.setCode(errorCode);
        e.setMassage(msg);

        return e;
    }

}
