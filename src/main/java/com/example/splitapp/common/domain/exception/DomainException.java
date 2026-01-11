package com.example.splitapp.common.domain.exception;

public abstract class DomainException extends RuntimeException {
    private final ExceptionStatus exceptionStatus;
    private final ExceptionLevel exceptionLevel;

    public DomainException(String message, ExceptionStatus exceptionStatus, ExceptionLevel exceptionLevel) {
        super(message);
        this.exceptionStatus = exceptionStatus;
        this.exceptionLevel = exceptionLevel;
    }

    public ExceptionStatus getExceptionStatus() {
        return exceptionStatus;
    }

    public ExceptionLevel getExceptionLevel() {
        return exceptionLevel;
    }
}
