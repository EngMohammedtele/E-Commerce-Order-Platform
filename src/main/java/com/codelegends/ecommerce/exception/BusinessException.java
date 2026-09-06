package com.codelegends.ecommerce.exception;
// Represents an error case that the API can report clearly
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
