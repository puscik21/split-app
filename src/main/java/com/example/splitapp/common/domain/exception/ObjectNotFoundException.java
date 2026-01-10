package com.example.splitapp.common.domain.exception;

import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends ApiException {

    public ObjectNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, Level.INFO);
    }
}
