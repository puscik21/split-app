package com.example.splitapp.mapper;

import com.example.splitapp.dto.splitgroup.SplitGroupDTO;
import com.example.splitapp.model.SplitGroup;
import com.example.splitapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface SplitGroupMapper {

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
