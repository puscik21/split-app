package com.example.splitapp.user.core.domain.exception;

import com.example.splitapp.common.domain.exception.DomainException;
import com.example.splitapp.common.domain.exception.ExceptionLevel;
import com.example.splitapp.common.domain.exception.ExceptionStatus;
import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException(String login) {
        super("Unable to create. A user with login '%s' already exists.".formatted(login), ExceptionStatus.CONFLICT, ExceptionLevel.WARN);
    }
}
