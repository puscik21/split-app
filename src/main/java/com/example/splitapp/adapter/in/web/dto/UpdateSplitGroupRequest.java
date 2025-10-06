package com.example.splitapp.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data for updating an existing split group")
public record UpdateSplitGroupRequest(
        @Schema(description = "The new title for the group", example = "Seaside Vacation")
        String title,

        @Schema(description = "The new description for the group", example = "Summer trip to Sopot")
        String description
) {
}
