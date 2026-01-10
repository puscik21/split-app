package com.example.splitapp.splitgroup.adapter.out.persistence.mapper;

import com.example.splitapp.splitgroup.adapter.out.persistence.entity.SplitGroupEntity;
import com.example.splitapp.splitgroup.core.domain.model.SplitGroup;
import com.example.splitapp.user.adapter.out.persistence.mapper.UserPersistenceMapper;
import org.mapstruct.Mapper;

@Mapper(uses = UserPersistenceMapper.class) // TODO: check if needed
public interface SplitGroupPersistenceMapper {

    SplitGroup toDomain(SplitGroupEntity entity);

    SplitGroupEntity toEntity(SplitGroup domain);
}
