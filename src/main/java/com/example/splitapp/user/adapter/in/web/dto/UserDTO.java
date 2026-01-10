package com.example.splitapp.user.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Public information about a user")
public record UserDTO(
        @Schema(description = "The unique user login", example = "jane.doe")
        String login,

        @Schema(description = "A set of IDs of split groups the user belongs to")
        Set<Long> splitGroupIds
) {
}
