package com.example.tubespbo.tubespbo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;

//ini buat ngehandle exception kek ngasih httpstatus gitu
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ApiResponseMapper responseBuilder;

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthException(AuthException ex) {
        ApiResponse<Object> response = responseBuilder.ToApiResponse(HttpStatus.UNAUTHORIZED,ex.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException ex) {
        ApiResponse<Object> response = responseBuilder.ToApiResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        ApiResponse<Object> response = responseBuilder.ToApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // klo ini bisa jadi validator dari request, lihat anotasi @Valid di parameter controller
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder errorMsg = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
        //br untuk memberi jarak antar pesan errornya di html
            errorMsg.append(error.getDefaultMessage()).append("<br>")
            // errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage())
        );
        ApiResponse<Object> response = responseBuilder.ToApiResponse(HttpStatus.BAD_REQUEST,errorMsg.toString(),null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
