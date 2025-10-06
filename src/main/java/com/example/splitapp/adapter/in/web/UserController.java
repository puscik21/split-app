package com.example.splitapp.adapter.in.web;

import com.example.splitapp.adapter.in.web.dto.UserDTO;
import com.example.splitapp.adapter.in.web.dto.UserRegistrationRequest;
import com.example.splitapp.adapter.in.web.mapper.UserDTOMapper;
import com.example.splitapp.adapter.in.web.utils.ControllerUtils;
import com.example.splitapp.core.domain.User;
import com.example.splitapp.core.port.in.UserServicePort;
import com.example.splitapp.core.port.in.command.RegisterUserCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Users", description = "API for user management and registration")
public class UserController {

    private final UserServicePort userService;
    private final UserDTOMapper dtoMapper;

    private static final Set<String> ALLOWED_SORT_BY_FIELDS = Set.of("login");

    @Operation(summary = "Find and filter users", description = "Returns a list of users, optionally filtered by a key. Allows sorting by login.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users")
    @GetMapping
    public List<UserDTO> findUsers(
            @Parameter(description = "Search key to filter users by login (case-insensitive)", example = "john") @RequestParam(required = false) String key,
            @Parameter(description = "Field to sort by. Allowed values: 'login'", example = "login") @RequestParam(defaultValue = "login") String sortBy,
            @Parameter(description = "Sort direction (ASC or DESC)", example = "ASC") @RequestParam(defaultValue = "ASC") String sortOrder) {
        if (!ALLOWED_SORT_BY_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sortBy " + sortBy);
        }
        return userService.findAll(key, sortBy, sortOrder).stream()
                .map(dtoMapper::toDto)
                .toList();
    }

    @Operation(summary = "Get a single user by their login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "User with the given login does not exist", content = @Content)
    })
    @GetMapping("/{login}")
    public UserDTO getUser(
            @Parameter(description = "Login of the user to retrieve", required = true, example = "jane.doe") @PathVariable String login) {
        return dtoMapper.toDto(userService.getByLogin(login));
    }

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data (e.g., password too short)", content = @Content),
            @ApiResponse(responseCode = "409", description = "A user with this login already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        RegisterUserCommand registerCommand = new RegisterUserCommand(registrationRequest.login(), registrationRequest.password());
        User registeredUser = userService.register(registerCommand);
        UserDTO userDto = dtoMapper.toDto(registeredUser);
        URI location = ControllerUtils.getLocation("/{login}", userDto.login());
        return ResponseEntity.created(location).body(userDto);
    }

    @Operation(summary = "Delete a user by their login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User with the given login does not exist", content = @Content)
    })
    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "Login of the user to delete", required = true, example = "jane.doe") @PathVariable String login) {
        userService.removeByLogin(login);
        return ResponseEntity.noContent().build();
    }
}
