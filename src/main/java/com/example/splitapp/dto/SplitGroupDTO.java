package com.example.splitapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record SplitGroupDTO(
        Long id,
        String title,
        String description,
        LocalDate date,
        LocalDateTime creationTimestamp,
        Set<String> userLogins) {
}
