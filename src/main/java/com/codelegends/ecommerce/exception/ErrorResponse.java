package com.codelegends.ecommerce.exception;
import java.time.LocalDateTime;
// Carries ErrorResponse details in a small immutable response object
public record ErrorResponse(int status,String error,String message,LocalDateTime timestamp) {
}
