package com.example.splitapp.splitgroup.core.domain.model;

import com.example.splitapp.user.core.domain.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SplitGroup {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationTimestamp;
    private final Set<User> users = new HashSet<>();

    public SplitGroup() {
    }

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
        return !this.users.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SplitGroup that = (SplitGroup) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
