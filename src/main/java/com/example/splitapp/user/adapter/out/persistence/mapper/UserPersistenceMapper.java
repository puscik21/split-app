package com.example.splitapp.user.adapter.out.persistence.mapper;

import com.example.splitapp.splitgroup.adapter.out.persistence.mapper.SplitGroupPersistenceMapper;
import com.example.splitapp.user.adapter.out.persistence.entity.UserEntity;
import com.example.splitapp.user.core.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(uses = SplitGroupPersistenceMapper.class)
public interface UserPersistenceMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User userEntity);
}
