package com.example.splitapp.splitgroup.core.domain.exception;

import com.example.splitapp.common.domain.exception.DomainException;
import com.example.splitapp.common.domain.exception.ExceptionLevel;
import com.example.splitapp.common.domain.exception.ExceptionStatus;

public class UserNotFoundInSplitGroupException extends DomainException {

    public UserNotFoundInSplitGroupException(Long id, String login) {
        super("A user with login '%s' was not found in '%d' split group.".formatted(login, id), ExceptionStatus.NOT_FOUND, ExceptionLevel.WARN);
    }
}
