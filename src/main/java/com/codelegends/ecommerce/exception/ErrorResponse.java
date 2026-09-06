package com.codelegends.ecommerce.exception;
// Use LocalDateTime to show when the error happened
import java.time.LocalDateTime;
// Carries ErrorResponse details in a small immutable response object
// Defines the error body returned by the API
// Store the HTTP status number in the response
// Hold the short HTTP error name
// Carry the detailed message sent to the client
// Record the time when the response is created
public record ErrorResponse(int status,String error,String message,LocalDateTime timestamp) {
}
