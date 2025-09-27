package com.example.splitapp.exception;

import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

public class UserNotFoundInSplitGroupException extends ApiException {

    public UserNotFoundInSplitGroupException(Long id, String login) {
        super("A user with login '%s' was not found in '%d' split group.".formatted(login, id), HttpStatus.NOT_FOUND, Level.WARN);
    }
}
