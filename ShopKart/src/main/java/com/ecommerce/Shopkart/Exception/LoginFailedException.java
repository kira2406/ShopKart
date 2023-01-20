package com.ecommerce.Shopkart.Exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class LoginFailedException extends RuntimeException {

    private String message;

    public LoginFailedException() {}

    public LoginFailedException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}