package com.codelegends.ecommerce.exception;
// Represents an error case that the API can report clearly
// Represents a business rule problem in the app
// Extend RuntimeException so callers are not forced to catch it
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
