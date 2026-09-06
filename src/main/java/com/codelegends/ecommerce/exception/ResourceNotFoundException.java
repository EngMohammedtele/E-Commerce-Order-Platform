package com.codelegends.ecommerce.exception;
// Represents an error case that the API can report clearly
// Represents an error when requested data is missing
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
