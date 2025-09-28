package com.example.splitapp.service;

import com.example.splitapp.dto.UserDTO;
import com.example.splitapp.dto.user.UserRegistrationRequest;
import com.example.splitapp.exception.ObjectNotFoundException;
import com.example.splitapp.exception.UserAlreadyExistsException;
import com.example.splitapp.mapper.UserMapper;
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
    private final UserMapper userMapper;
//    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDTO getUserDTOByLogin(String login) {
        return userMapper.toDto(getUserByLogin(login));
    }

    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ObjectNotFoundException("User with login '%s' not found".formatted(login)));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(String filter, String sortBy, Sort.Direction sortOrder) {
        Sort sort = Sort.by(sortOrder, sortBy);
        if (StringUtils.hasText(filter)) {
            return userRepository.findAllByLoginContainingIgnoreCase(filter, sort).stream()
                    .map(userMapper::toDto)
                    .toList();
        }
        return userRepository.findAll(sort).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    public UserDTO register(UserRegistrationRequest registrationRequest) {
        userRepository.findByLogin(registrationRequest.login()).ifPresent(p -> {
            throw new UserAlreadyExistsException(registrationRequest.login());
        });
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user = new User(registrationRequest.login(), registrationRequest.password());
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public void removeByLogin(String login) {
        User user = getUserByLogin(login);
        userRepository.delete(user);
    }
}
