package com.ecommerce.Shopkart.Exception;

public class UsernameNotFoundException extends RuntimeException{
    private String message;

    public UsernameNotFoundException() {}

    public UsernameNotFoundException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}