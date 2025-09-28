package com.example.splitapp.mapper;

import com.example.splitapp.dto.UserDTO;
import com.example.splitapp.model.SplitGroup;
import com.example.splitapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    @Mapping(source = "splitGroups", target = "splitGroupIds", qualifiedByName = "splitGroupsToIds")
    UserDTO toDto(User user);

    @Named("splitGroupsToIds")
    default Set<Long> splitGroupsToIds(Set<SplitGroup> splitGroups) {
        if (splitGroups == null || splitGroups.isEmpty()) {
            return Collections.emptySet();
        }
        return splitGroups.stream()
                .map(SplitGroup::getId)
                .collect(Collectors.toSet());
    }
}
