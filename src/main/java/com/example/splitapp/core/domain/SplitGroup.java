package com.example.splitapp.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SplitGroup {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationTimestamp;
    private Set<User> users;

    public SplitGroup(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addUser(User user) {
        // TODO: possibly add some validations
        this.users.add(user);
    }

    public void removeUser(User user) {
        // TODO: possibly add some validations
        this.users.remove(user);
    }

    public boolean hasUsers() {
        return this.users != null && !this.users.isEmpty();
    }
}
