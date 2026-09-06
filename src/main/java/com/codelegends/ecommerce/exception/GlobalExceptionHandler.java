package com.codelegends.ecommerce.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.stream.*;
// Apply these handlers to REST controllers across the app
@RestControllerAdvice
// Defines the GlobalExceptionHandler type used by the ecommerce app
// Central place for turning exceptions into HTTP responses
public class GlobalExceptionHandler {
    // Handle this kind of exception in one place
    // Catch missing resource errors here
    // Receive the exception that says a record was not found
    @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException e) {
        // Return the result to the calling code
        // Send a 404 response with the exception message
        return response(HttpStatus.NOT_FOUND,e.getMessage());
    }
    // Handle this kind of exception in one place
    @ExceptionHandler( {
        // Group bad request exception types together
        BusinessException.class,IllegalArgumentException.class
    }
    // Handle client-side request problems with one method
    ) ResponseEntity<ErrorResponse> badRequest(RuntimeException e) {
        // Return the result to the calling code
        // Return HTTP 400 for invalid request data
        return response(HttpStatus.BAD_REQUEST,e.getMessage());
    }
    // Handle this kind of exception in one place
    // Catch validation errors from request bodies
    // Receive validation details collected by Spring
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e) {
        // Collect validation messages into one readable string
        // Read all field validation errors from the binding result
        // Join field names and messages into one response string
        String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining(", "));
        // Return the result to the calling code
        // Send validation failures back as a bad request
        return response(HttpStatus.BAD_REQUEST,m);
    }
    // Handle this kind of exception in one place
    // Catch any error that was not handled above
    @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponse> generic(Exception e) {
        // Return the result to the calling code
        return response(HttpStatus.INTERNAL_SERVER_ERROR,"Unexpected server error");
    }
    private ResponseEntity<ErrorResponse> response(HttpStatus s,String m) {
        // Send the HTTP response with the chosen status
        return ResponseEntity.status(s).body(new ErrorResponse(s.value(),s.getReasonPhrase(),m,LocalDateTime.now()));
    }
}
