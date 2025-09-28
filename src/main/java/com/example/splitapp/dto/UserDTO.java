package com.example.splitapp.dto;

import java.util.Set;

public record UserDTO(String login, Set<Long> splitGroupIds) {
}
