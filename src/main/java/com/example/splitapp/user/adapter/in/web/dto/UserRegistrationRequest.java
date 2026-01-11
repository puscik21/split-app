package com.example.splitapp.user.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data required to register a new user")
public record UserRegistrationRequest(
        @Schema(description = "The desired user login (must be unique)", example = "john.doe", requiredMode = Schema.RequiredMode.REQUIRED)
        String login,

        @Schema(description = "User's password", example = "Str0ngP@ssw0rd!", format = "password", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {
}
