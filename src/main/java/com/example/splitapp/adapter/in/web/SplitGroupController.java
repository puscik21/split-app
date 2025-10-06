package com.example.splitapp.adapter.in.web;

import com.example.splitapp.adapter.in.web.dto.CreateSplitGroupRequest;
import com.example.splitapp.adapter.in.web.dto.SplitGroupDTO;
import com.example.splitapp.adapter.in.web.dto.UpdateSplitGroupRequest;
import com.example.splitapp.adapter.in.web.mapper.SplitGroupDTOMapper;
import com.example.splitapp.adapter.in.web.utils.ControllerUtils;
import com.example.splitapp.core.domain.SplitGroup;
import com.example.splitapp.core.port.in.SplitGroupServicePort;
import com.example.splitapp.core.port.in.command.CreateSplitGroupCommand;
import com.example.splitapp.core.port.in.command.UpdateSplitGroupCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/split-groups")
@RequiredArgsConstructor
@Tag(name = "Split Groups", description = "API for managing split groups")
public class SplitGroupController {

    private final SplitGroupServicePort splitGroupService;
    private final SplitGroupDTOMapper dtoMapper;

    @Operation(summary = "Search and filter split groups", description = "Returns a list of groups matching the given filtering and sorting criteria.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of groups")
    @GetMapping
    public List<SplitGroupDTO> findSplitGroups(
            @Parameter(description = "Group title fragment to search for", example = "vacation") @RequestParam(value = "title", defaultValue = "") String title,
            @Parameter(description = "Group description fragment to search for", example = "Croatia") @RequestParam(value = "description", defaultValue = "") String description,
            @Parameter(description = "Field to sort by", example = "title") @RequestParam(value = "sortBy", defaultValue = "title") String sortBy,
            @Parameter(description = "Login of a user belonging to the group", example = "user123") @RequestParam(value = "userLogin", defaultValue = "") String userLogin,
            @Parameter(description = "Sort direction (ASC or DESC)", example = "DESC") @RequestParam(defaultValue = "ASC") String sortOrder) {
        return splitGroupService.findSplitGroups(title, description, userLogin, sortBy, sortOrder).stream()
                .map(dtoMapper::toDto)
                .toList();
    }


    @Operation(summary = "Get a single group by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group found", content = @Content(schema = @Schema(implementation = SplitGroupDTO.class))),
            @ApiResponse(responseCode = "404", description = "Group with the given ID does not exist", content = @Content)
    })
    @GetMapping("/{groupId}")
    public SplitGroupDTO getSplitGroupById(
            @Parameter(description = "ID of the group to retrieve", required = true, example = "1") @PathVariable Long groupId) {
        SplitGroup domainGroup = splitGroupService.getById(groupId);
        return dtoMapper.toDto(domainGroup);
    }

    @Operation(summary = "Create a new split group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Group created successfully", content = @Content(schema = @Schema(implementation = SplitGroupDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data (e.g., empty title)", content = @Content)
    })
    @PostMapping
    public ResponseEntity<SplitGroupDTO> createSplitGroup(@RequestBody CreateSplitGroupRequest createRequest) {
        CreateSplitGroupCommand createCommand = new CreateSplitGroupCommand(createRequest.title(), createRequest.description());
        SplitGroup createdDomainGroup = splitGroupService.add(createCommand);
        SplitGroupDTO responseDto = dtoMapper.toDto(createdDomainGroup);
        URI location = ControllerUtils.getLocation("/{groupId}", responseDto.id());
        return ResponseEntity.created(location).body(responseDto);
    }

    @Operation(summary = "Update group data (partial update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group updated successfully", content = @Content(schema = @Schema(implementation = SplitGroupDTO.class))),
            @ApiResponse(responseCode = "404", description = "Group with the given ID does not exist", content = @Content)
    })
    @PatchMapping("/{groupId}")
    public SplitGroupDTO updateSplitGroup(
            @Parameter(description = "ID of the group to update", required = true, example = "1") @PathVariable Long groupId,
            @RequestBody UpdateSplitGroupRequest updateRequest) {
        UpdateSplitGroupCommand updateCommand = new UpdateSplitGroupCommand(updateRequest.title(), updateRequest.description());
        SplitGroup updatedDomainGroup = splitGroupService.update(groupId, updateCommand);
        return dtoMapper.toDto(updatedDomainGroup);
    }

    @Operation(summary = "Delete a group by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Group was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Group with the given ID does not exist")
    })
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteSplitGroup(
            @Parameter(description = "ID of the group to delete", required = true, example = "1") @PathVariable Long groupId) {
        splitGroupService.deleteById(groupId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all user logins from a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of logins"),
            @ApiResponse(responseCode = "404", description = "Group with the given ID does not exist")
    })
    @GetMapping("/{groupId}/users")
    public Set<String> getSplitGroupsUsers(
            @Parameter(description = "ID of the group", required = true, example = "1") @PathVariable Long groupId) {
        return getSplitGroupById(groupId).userLogins();
    }

    @Operation(summary = "Add a user to a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully added to the group"),
            @ApiResponse(responseCode = "404", description = "Group or user does not exist"),
            @ApiResponse(responseCode = "409", description = "User is already in this group (Conflict)")
    })
    @PostMapping("/{groupId}/users/{login}")
    public ResponseEntity<Void> addUserToGroup(
            @Parameter(description = "ID of the group", required = true, example = "1") @PathVariable Long groupId,
            @Parameter(description = "Login of the user to add", required = true, example = "new_user") @PathVariable String login) {
        splitGroupService.addUserToGroup(groupId, login);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove a user from a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully removed from the group"),
            @ApiResponse(responseCode = "404", description = "Group or user does not exist")
    })
    @DeleteMapping("/{groupId}/users/{login}")
    public ResponseEntity<Void> deleteUserFromGroup(
            @Parameter(description = "ID of the group", required = true, example = "1") @PathVariable Long groupId,
            @Parameter(description = "Login of the user to remove", required = true, example = "old_user") @PathVariable String login) {
        splitGroupService.deleteUserFromGroup(groupId, login);
        return ResponseEntity.noContent().build();
    }
}
