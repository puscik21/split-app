package com.example.splitapp.adapter.in.web.mapper;

import com.example.splitapp.adapter.in.web.dto.UserDTO;
import com.example.splitapp.core.domain.SplitGroup;
import com.example.splitapp.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserDTOMapper {

    // TODO: simple method, remove after fixing group Ids in findAll()
    @Mapping(target = "splitGroupIds", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "login", source = "user.login")
    @Mapping(source = "splitGroups", target = "splitGroupIds")
    UserDTO toDto(User user, List<SplitGroup> splitGroups);

    default Set<Long> mapGroupsToIds(List<SplitGroup> splitGroups) {
        if (splitGroups == null || splitGroups.isEmpty()) {
            return Collections.emptySet();
        }
        return splitGroups.stream()
                .map(SplitGroup::getId)
                .collect(Collectors.toSet());
    }
}
