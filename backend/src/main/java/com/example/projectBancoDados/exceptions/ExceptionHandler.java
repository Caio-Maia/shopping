package com.example.projectBancoDados.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Exception exception = new Exception();
        List<String> error = new ArrayList<>();
        exception.setStatus(status.value());
        exception.setTime(LocalDateTime.now());
        ex.getFieldErrors().forEach(fieldError -> {
            error.add(fieldError.getDefaultMessage());
        });
        exception.setErrors(error);
        return handleExceptionInternal(ex, exception, headers, status, request);
    }
}
