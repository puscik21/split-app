package com.example.splitapp.user.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Public information about a user")
public record UserDTO(
        @Schema(description = "The unique user login", example = "jane.doe")
        String login
) {
}
