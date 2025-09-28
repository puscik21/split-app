package com.example.splitapp.controller;

import com.example.splitapp.controller.utils.ControllerUtils;
import com.example.splitapp.model.User;
import com.example.splitapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private static final Set<String> ALLOWED_SORT_BY_FIELDS = Set.of("login");

    @GetMapping
    public List<User> findUsers(@RequestParam(required = false) String key,
                                @RequestParam(defaultValue = "login") String sortBy,
                                @RequestParam(defaultValue = "ASC") String sortOrder) {
        if (!ALLOWED_SORT_BY_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sortBy " + sortBy);
        }
        return userService.findAll(key, sortBy, Sort.Direction.valueOf(sortOrder));
    }

    @GetMapping("/{login}")
    public User getUser(@PathVariable String login) {
        return userService.getByLogin(login);
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.register(user);
        URI location = ControllerUtils.getLocation("/{login}", registeredUser.getLogin());
        return ResponseEntity.created(location).body(registeredUser);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.removeByLogin(login);
        return ResponseEntity.noContent().build();
    }
}
