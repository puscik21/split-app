package com.example.splitapp.adapter.out.persistence.mapper;

import com.example.splitapp.adapter.out.persistence.entity.UserEntity;
import com.example.splitapp.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(uses = SplitGroupPersistenceMapper.class)
public interface UserPersistenceMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User userEntity);
}
