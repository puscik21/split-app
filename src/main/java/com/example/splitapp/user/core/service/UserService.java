package com.example.splitapp.user.core.service;

import com.example.splitapp.user.core.domain.model.User;
import com.example.splitapp.user.core.port.in.UserServicePort;
import com.example.splitapp.user.core.port.in.command.RegisterUserCommand;
import com.example.splitapp.user.core.port.out.UserRepositoryPort;
import com.example.splitapp.common.domain.exception.ObjectNotFoundException;
import com.example.splitapp.user.core.domain.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ObjectNotFoundException("User with login '%s' not found".formatted(login)));
    }

    @Transactional(readOnly = true)
    public List<User> findAll(String loginFilter, String sortBy, String sortOrder) {
        return userRepository.findAll(loginFilter, sortBy, sortOrder);
    }

    @Transactional
    public User register(RegisterUserCommand registerCommand) {
        userRepository.findByLogin(registerCommand.login()).ifPresent(p -> {
            throw new UserAlreadyExistsException(registerCommand.login());
        });
//        String hashedPassword = passwordEncoder.encode(registerCommand.password());
        User user = new User(registerCommand.login(), registerCommand.password());
        return userRepository.save(user);
    }

    @Transactional
    public void removeByLogin(String login) {
        getByLogin(login); // TODO: existsByLogin() might be better
        userRepository.deleteByLogin(login);
    }
}
