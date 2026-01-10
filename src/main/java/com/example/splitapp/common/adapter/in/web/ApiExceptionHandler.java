package com.example.splitapp.common.adapter.in.web;

import com.example.splitapp.common.domain.exception.DomainException;
import com.example.splitapp.common.domain.exception.ExceptionLevel;
import com.example.splitapp.common.domain.exception.ExceptionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<ExceptionResponse> handleRequestException(DomainException e) {
        String message = "Sending response %s. %s".formatted(e.getExceptionStatus().toString(), e.getMessage());
        return logAndPrepareResponse(e, e.getExceptionStatus(), e.getExceptionLevel(), message);
    }

    private static ResponseEntity<ExceptionResponse> logAndPrepareResponse(RuntimeException e, ExceptionStatus status, ExceptionLevel logLevel, String message) {
        HttpStatus httpStatus = toHttpStatus(status);
        ExceptionResponse result = new ExceptionResponse(e.getMessage(), httpStatus, Instant.now());
        logAtLevel(logLevel, message);
        return ResponseEntity.status(httpStatus).body(result);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        String message = String.format("Failed to handle request: %s, status: %s, stacktrace: ", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return logWithStackTraceAndPrepareResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private static ResponseEntity<ExceptionResponse> logWithStackTraceAndPrepareResponse(Exception e, HttpStatus status, String message) {
        ExceptionResponse result = new ExceptionResponse(e.getMessage(), status, Instant.now());
        log.error(message, e);
        return ResponseEntity.status(status).body(result);
    }

    private static HttpStatus toHttpStatus(ExceptionStatus status) {
        return switch (status) {
            case CONFLICT -> HttpStatus.CONFLICT;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case BAD_REQUEST -> HttpStatus.BAD_REQUEST;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
        };
    }

    private static void logAtLevel(ExceptionLevel logLevel, String message) {
        switch (logLevel) {
            case INFO -> log.info(message);
            case WARN -> log.warn(message);
            case ERROR -> log.error(message);
        }
    }
}
