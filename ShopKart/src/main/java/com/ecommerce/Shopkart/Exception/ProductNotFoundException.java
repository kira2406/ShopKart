package com.ecommerce.Shopkart.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) { super(msg);
    }
}
