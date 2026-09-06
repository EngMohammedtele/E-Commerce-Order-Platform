package com.codelegends.ecommerce.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.stream.*;
// Apply these handlers to REST controllers across the app
@RestControllerAdvice
// Defines the GlobalExceptionHandler type used by the ecommerce app
public class GlobalExceptionHandler {
    // Handle this kind of exception in one place
    @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException e) {
        // Return the result to the calling code
        return response(HttpStatus.NOT_FOUND,e.getMessage());
    }
    // Handle this kind of exception in one place
    @ExceptionHandler( {
        BusinessException.class,IllegalArgumentException.class
    }
    ) ResponseEntity<ErrorResponse> badRequest(RuntimeException e) {
        // Return the result to the calling code
        return response(HttpStatus.BAD_REQUEST,e.getMessage());
    }
    // Handle this kind of exception in one place
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e) {
        // Collect validation messages into one readable string
        String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining(", "));
        // Return the result to the calling code
        return response(HttpStatus.BAD_REQUEST,m);
    }
    // Handle this kind of exception in one place
    @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponse> generic(Exception e) {
        // Return the result to the calling code
        return response(HttpStatus.INTERNAL_SERVER_ERROR,"Unexpected server error");
    }
    private ResponseEntity<ErrorResponse> response(HttpStatus s,String m) {
        // Send the HTTP response with the chosen status
        return ResponseEntity.status(s).body(new ErrorResponse(s.value(),s.getReasonPhrase(),m,LocalDateTime.now()));
    }
}
