package com.example.splitapp.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ExceptionResult(String message, HttpStatus httpStatus, Instant time) {
}

