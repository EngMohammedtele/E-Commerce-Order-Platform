package com.codelegends.ecommerce.exception;
// Use LocalDateTime to show when the error happened
import java.time.LocalDateTime;
// Carries ErrorResponse details in a small immutable response object
public record ErrorResponse(int status,String error,String message,LocalDateTime timestamp) {
}
