package com.ecommerce.Shopkart.Exception;


public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String msg)
    {
        super(msg);
    }
}