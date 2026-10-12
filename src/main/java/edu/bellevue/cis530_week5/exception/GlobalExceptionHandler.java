
package edu.bellevue.cis530_week5.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(
          MethodArgumentNotValidException ex,
          HttpServletRequest request) {

    Map<String, String> validationErrors =
            new LinkedHashMap<>();

    ex.getBindingResult().getFieldErrors()
            .forEach(error ->
                    validationErrors.putIfAbsent(
                            error.getField(),
                            error.getDefaultMessage()
                    )
            );

    Map<String, Object> response = new LinkedHashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Bad Request");
    response.put("message", "Validation failed");
    response.put("validationErrors", validationErrors);
    response.put("path", request.getRequestURI());

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(
          ResourceNotFoundException ex,
          HttpServletRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("error", "Not Found");
    response.put("message", ex.getMessage());
    response.put("path", request.getRequestURI());

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>> handleBadRequest(
          IllegalArgumentException ex,
          HttpServletRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Bad Request");
    response.put("message", ex.getMessage());
    response.put("path", request.getRequestURI());

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, Object>> handleInvalidJson(
          HttpMessageNotReadableException ex,
          HttpServletRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Bad Request");
    response.put("message", "Invalid request JSON");
    response.put("path", request.getRequestURI());

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
  }
}
