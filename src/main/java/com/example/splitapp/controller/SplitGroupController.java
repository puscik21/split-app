package com.example.splitapp.controller;

import com.example.splitapp.controller.utils.ControllerUtils;
import com.example.splitapp.model.SplitGroup;
import com.example.splitapp.model.User;
import com.example.splitapp.service.SplitGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class SplitGroupController {

    private final SplitGroupService splitGroupService;

    @GetMapping
    public List<SplitGroup> findSplitGroups(@RequestParam(value = "title", defaultValue = "") String title,
                                            @RequestParam(value = "description", defaultValue = "") String description,
                                            @RequestParam(value = "sortBy", defaultValue = "title") String sortBy,
                                            @RequestParam(value = "userLogin", defaultValue = "") String userLogin,
                                            @RequestParam(defaultValue = "ASC") String sortOrder) {
        return splitGroupService.findSplitGroups(title, description, userLogin, sortBy, Sort.Direction.valueOf(sortOrder));
    }

    @GetMapping("/{groupId}")
    public SplitGroup getSplitGroupById(@PathVariable Long groupId) {
        return splitGroupService.getById(groupId);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteSplitGroup(@PathVariable Long groupId) {
        splitGroupService.deleteById(groupId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<SplitGroup> createSplitGroup(@RequestBody SplitGroup splitGroup) {
        SplitGroup created = splitGroupService.add(splitGroup);
        URI location = ControllerUtils.getLocation("/{groupId}", created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{groupId}")
    public SplitGroup updateSplitGroup(@PathVariable Long groupId, @RequestBody SplitGroup splitGroup) {
        return splitGroupService.update(groupId, splitGroup);
    }

    @GetMapping("/{groupId}/users")
    public Set<User> getSplitGroupsUsers(@PathVariable Long groupId) {
        return splitGroupService.getById(groupId).getUsers();
    }

    @PostMapping("/{groupId}/users/{login}")
    public ResponseEntity<Void> addUser(@PathVariable Long groupId, @PathVariable String login) {
        splitGroupService.addUser(groupId, login);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{groupId}/users/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long groupId, @PathVariable String login) {
        splitGroupService.deleteUser(groupId, login);
        return ResponseEntity.noContent().build();
    }
}
