package com.ecommerce.Shopkart.Exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ecommerce.Shopkart.Dto.ErrorResponseDTO;
import com.ecommerce.Shopkart.Dto.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse<?> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        GeneralResponse<?> response = new GeneralResponse<>();
        List<ErrorResponseDTO> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors() // [ [field, error], [field, error]]
                .forEach(error -> {
                    ErrorResponseDTO errorDTO = new ErrorResponseDTO(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        response.setStatus("FAILED");
        response.setErrors(errors);
        return response;
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralResponse<?> handleLoginFailedException(LoginFailedException ex){
        return GeneralResponse.builder()
                .status("FAILED")
                .errors(Collections.singletonList(new ErrorResponseDTO("", ex.getMessage()))).build();
    }

    @ExceptionHandler(ControllerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse<?> handleControllerException(ControllerException ex){
        return GeneralResponse.builder()
                .status("FAILED")
                .errors(Collections.singletonList(new ErrorResponseDTO("", ex.getMessage()))).build();
    }
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse<?> handleProductNotFoundException(ProductNotFoundException ex) {
        return GeneralResponse.builder()
                .status("FAILED")
                .errors(Collections.singletonList(new ErrorResponseDTO("", ex.getMessage()))).build();
    }

    @ExceptionHandler(ProductServiceBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse<?> handleServiceException(ProductServiceBusinessException ex) {
        return GeneralResponse.builder()
                .status("FAILED")
                .errors(Collections.singletonList(new ErrorResponseDTO("", ex.getMessage()))).build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return GeneralResponse.builder()
                .status("FAILED")
                .errors(Collections.singletonList(new ErrorResponseDTO("", ex.getMessage()))).build();
    }



}
