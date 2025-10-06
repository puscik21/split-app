package com.example.splitapp.core.port.in;

import com.example.splitapp.core.domain.User;
import com.example.splitapp.core.port.in.command.RegisterUserCommand;

import java.util.List;

public interface UserServicePort {

    User getByLogin(String login);

    List<User> findAll(String loginFilter, String sortBy, String sortOrder);

    User register(RegisterUserCommand registerCommand);

    void removeByLogin(String login);
}
