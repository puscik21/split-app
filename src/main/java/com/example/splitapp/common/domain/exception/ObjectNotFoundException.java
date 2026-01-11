package com.example.splitapp.common.domain.exception;

public class ObjectNotFoundException extends DomainException {

    public ObjectNotFoundException(String message) {
        super(message, ExceptionStatus.NOT_FOUND, ExceptionLevel.INFO);
    }
}
