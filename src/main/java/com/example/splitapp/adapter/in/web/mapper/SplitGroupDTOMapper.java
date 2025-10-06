package com.example.splitapp.adapter.in.web.mapper;

import com.example.splitapp.adapter.in.web.dto.SplitGroupDTO;
import com.example.splitapp.core.domain.SplitGroup;
import com.example.splitapp.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface SplitGroupDTOMapper {

    @Mapping(source = "users", target = "userLogins", qualifiedByName = "usersToLogins")
    SplitGroupDTO toDto(SplitGroup group);

    @Named("usersToLogins")
    default Set<String> usersToLogins(Set<User> users) {
        if (users == null || users.isEmpty()) {
            return Collections.emptySet();
        }
        return users.stream()
                .map(User::getLogin)
                .collect(Collectors.toSet());
    }
}
