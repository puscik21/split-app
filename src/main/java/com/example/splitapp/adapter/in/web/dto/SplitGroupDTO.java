package com.example.splitapp.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "Detailed information about a split group")
public record SplitGroupDTO(
        @Schema(description = "The unique identifier of the group", example = "101")
        Long id,

        @Schema(description = "Title of the group", example = "Grocery Shopping")
        String title,

        @Schema(description = "Description of the group", example = "Weekly grocery shopping")
        String description,

        @Schema(description = "Date associated with the group (e.g., trip date)", example = "2025-09-29")
        LocalDate date,

        @Schema(description = "Timestamp of when the group was created", example = "2025-09-29T21:27:40.700235")
        LocalDateTime creationTimestamp,

        @Schema(description = "A set of user logins belonging to the group")
        Set<String> userLogins
) {
}
