package com.example.splitapp.splitgroup.core.domain.exception;


import com.example.splitapp.common.domain.exception.DomainException;
import com.example.splitapp.common.domain.exception.ExceptionLevel;
import com.example.splitapp.common.domain.exception.ExceptionStatus;

public class RemovalOfSplitGroupWithUserException extends DomainException {

    public RemovalOfSplitGroupWithUserException(String title) {
        super("Cannot delete split group '%s', as it have user".formatted(title), ExceptionStatus.CONFLICT, ExceptionLevel.WARN);
    }
}
