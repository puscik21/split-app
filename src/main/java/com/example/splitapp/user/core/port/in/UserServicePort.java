package com.example.splitapp.user.core.port.in;

import com.example.splitapp.user.core.domain.model.User;
import com.example.splitapp.user.core.port.in.command.RegisterUserCommand;

import java.util.List;

public interface UserServicePort {

    User getByLogin(String login);

    List<User> findAll(String loginFilter, String sortBy, String sortOrder);

    User register(RegisterUserCommand registerCommand);

    void removeByLogin(String login);
}
