package com.codelegends.ecommerce.exception;
// Represents an error case that the API can report clearly
// Represents a business rule problem in the app
// Extend RuntimeException so callers are not forced to catch it
public class BusinessException extends RuntimeException {
    // Create the exception with a readable message
    // Receive the text that explains the business failure
    public BusinessException(String message) {
        // Pass the message to the parent exception class
        super(message);
    }
}
