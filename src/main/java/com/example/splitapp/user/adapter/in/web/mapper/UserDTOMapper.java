package com.example.splitapp.user.adapter.in.web.mapper;

import com.example.splitapp.user.adapter.in.web.dto.UserDTO;
import com.example.splitapp.user.core.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserDTOMapper {

    @Mapping(source = "user.login", target = "login")
    UserDTO toDto(User user);
}
