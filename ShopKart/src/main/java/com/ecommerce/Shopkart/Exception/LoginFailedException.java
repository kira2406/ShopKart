package com.ecommerce.Shopkart.Exception;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String msg)
    {
        super(msg);
    }
}