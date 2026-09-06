package com.codelegends.ecommerce.exception;
// Represents an error case that the API can report clearly
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
