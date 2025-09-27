package com.example.splitapp.service;

import com.example.splitapp.exception.ObjectNotFoundException;
import com.example.splitapp.exception.UserAlreadyExistsException;
import com.example.splitapp.model.User;
import com.example.splitapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> findAll(String filter, String sortBy, Sort.Direction sortOrder) {
        Sort sort = Sort.by(sortOrder, sortBy);
        if (StringUtils.hasText(filter)) {
            return userRepository.findAllByLoginContainingIgnoreCase(filter, sort);
        }
        return userRepository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ObjectNotFoundException("User with login '%s' not found".formatted(login)));
    }

    @Transactional
    public User register(User user) {
        userRepository.findByLogin(user.getLogin()).ifPresent(p -> {
            throw new UserAlreadyExistsException(user.getLogin());
        });
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User update(String login, User user) {
        User existingUser = getByLogin(login);
        user.setLogin(existingUser.getLogin());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void removeByLogin(String login) {
        User user = getByLogin(login);
        userRepository.delete(user);
    }
}
