package com.example.splitapp.core.port.out;


import com.example.splitapp.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByLogin(String login);

    List<User> findAll(String loginFilter, String sortBy, String sortOrder);

    User save(User user);

    void deleteByLogin(String login);
}
