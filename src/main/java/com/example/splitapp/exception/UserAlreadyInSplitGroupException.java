package com.example.splitapp.exception;

import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

public class UserAlreadyInSplitGroupException extends ApiException {

    public UserAlreadyInSplitGroupException(Long id, String login) {
        super("A user with login '%s' already in '%d' split group.".formatted(login, id), HttpStatus.CONFLICT, Level.WARN);
    }
}
