package com.ecommerce.Shopkart.Exception;

public class ControllerException extends RuntimeException {

    private String message;

    public ControllerException() {}

    public ControllerException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
