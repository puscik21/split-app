package com.example.splitapp.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// TODO: add validation of fields
@Schema(description = "Data required to create a new split group")
public record CreateSplitGroupRequest(
        @Schema(description = "Title of the group", example = "Mountain Trip", requiredMode = Schema.RequiredMode.REQUIRED)
        String title,
        @Schema(description = "Optional description of the group", example = "Weekend trip to the Tatra Mountains with friends")
        String description
) {
}
