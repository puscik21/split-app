package com.example.splitapp.adapter.in.web.mapper;

import com.example.splitapp.adapter.in.web.dto.UserDTO;
import com.example.splitapp.core.domain.SplitGroup;
import com.example.splitapp.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserDTOMapper {

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
