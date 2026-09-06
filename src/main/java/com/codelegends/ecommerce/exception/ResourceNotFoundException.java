package com.codelegends.ecommerce.exception;
// Represents an error case that the API can report clearly
// Represents an error when requested data is missing
// Use RuntimeException so services can throw it directly
public class ResourceNotFoundException extends RuntimeException {
    // Create the exception with a clear not-found message
    // Receive the message that names the missing resource
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
