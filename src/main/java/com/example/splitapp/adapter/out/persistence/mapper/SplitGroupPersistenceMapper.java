package com.example.splitapp.adapter.out.persistence.mapper;

import com.example.splitapp.adapter.out.persistence.entity.SplitGroupEntity;
import com.example.splitapp.core.domain.SplitGroup;
import org.mapstruct.Mapper;

@Mapper(uses = UserPersistenceMapper.class) // TODO: check if needed
public interface SplitGroupPersistenceMapper {

    SplitGroup toDomain(SplitGroupEntity entity);

    SplitGroupEntity toEntity(SplitGroup domain);
}
