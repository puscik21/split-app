package com.example.splitapp.common.adapter.in.web;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ExceptionResponse(String message, HttpStatus httpStatus, Instant time) {
}

