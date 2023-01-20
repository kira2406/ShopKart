package com.ecommerce.Shopkart.Exception;

import java.time.LocalDateTime;

import com.ecommerce.Shopkart.Dto.CustomErrorResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    Environment env;
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody CustomErrorResponse handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new CustomErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody CustomErrorResponse handleLoginFailedException(LoginFailedException ex){
        return new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(ControllerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody CustomErrorResponse handleControllerException(ControllerException ex){
        return new CustomErrorResponse((HttpStatus.BAD_REQUEST.value()),ex.getMessage());
    }

}
