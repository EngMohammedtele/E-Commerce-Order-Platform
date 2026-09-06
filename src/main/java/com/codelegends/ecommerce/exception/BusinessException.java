package com.codelegends.ecommerce.exception;
// Represents an error case that the API can report clearly
// Represents a business rule problem in the app
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
