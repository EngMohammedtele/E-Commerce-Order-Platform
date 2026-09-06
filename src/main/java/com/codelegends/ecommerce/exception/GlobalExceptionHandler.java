package com.codelegends.ecommerce.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.stream.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException e) {
        return response(HttpStatus.NOT_FOUND,e.getMessage());
    }
    @ExceptionHandler( {
        BusinessException.class,IllegalArgumentException.class
    }
    ) ResponseEntity<ErrorResponse> badRequest(RuntimeException e) {
        return response(HttpStatus.BAD_REQUEST,e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e) {
        String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining(", "));
        return response(HttpStatus.BAD_REQUEST,m);
    }
    @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponse> generic(Exception e) {
        return response(HttpStatus.INTERNAL_SERVER_ERROR,"Unexpected server error");
    }
    private ResponseEntity<ErrorResponse> response(HttpStatus s,String m) {
        return ResponseEntity.status(s).body(new ErrorResponse(s.value(),s.getReasonPhrase(),m,LocalDateTime.now()));
    }
}
