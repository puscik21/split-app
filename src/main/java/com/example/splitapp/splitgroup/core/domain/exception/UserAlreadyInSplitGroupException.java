package com.example.splitapp.splitgroup.core.domain.exception;

import com.example.splitapp.common.domain.exception.DomainException;
import com.example.splitapp.common.domain.exception.ExceptionLevel;
import com.example.splitapp.common.domain.exception.ExceptionStatus;

public class UserAlreadyInSplitGroupException extends DomainException {

    public UserAlreadyInSplitGroupException(Long id, String login) {
        super("A user with login '%s' already in '%d' split group.".formatted(login, id), ExceptionStatus.CONFLICT, ExceptionLevel.WARN);
    }
}
