package com.maa.ecommerce.exceptions;

public class ErrorDetails {

    int code;
    String massage;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMassage()
    {
        return massage;
    }

    public void setMassage(String massage)
    {
        this.massage = massage;
    }
}
