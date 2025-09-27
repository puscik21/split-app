package com.example.splitapp.exception;

import lombok.Getter;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyExistsException extends ApiException {

    public UserAlreadyExistsException(String login) {
        super("Unable to create. A participant with login '%s' already exists.".formatted(login), HttpStatus.CONFLICT, Level.WARN);
    }
}
