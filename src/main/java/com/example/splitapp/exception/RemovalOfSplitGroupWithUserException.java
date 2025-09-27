package com.example.splitapp.exception;

import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

public class RemovalOfSplitGroupWithUserException extends ApiException {

    public RemovalOfSplitGroupWithUserException(String title) {
        super("Cannot delete split group '%s', as it have user".formatted(title), HttpStatus.CONFLICT, Level.WARN);
    }
}
