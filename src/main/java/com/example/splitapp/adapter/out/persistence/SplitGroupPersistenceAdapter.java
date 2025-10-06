package com.example.splitapp.adapter.out.persistence;

import com.example.splitapp.adapter.out.persistence.entity.SplitGroupEntity;
import com.example.splitapp.adapter.out.persistence.jpa.JpaSplitGroupRepository;
import com.example.splitapp.adapter.out.persistence.mapper.SplitGroupPersistenceMapper;
import com.example.splitapp.core.domain.SplitGroup;
import com.example.splitapp.core.port.out.SplitGroupRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SplitGroupPersistenceAdapter implements SplitGroupRepositoryPort {

    private final JpaSplitGroupRepository jpaRepository;
    private final SplitGroupPersistenceMapper persistenceMapper;

    @Override
    public Optional<SplitGroup> findById(Long id) {
        return jpaRepository.findById(id).map(persistenceMapper::toDomain);
    }

    @Override
    public List<SplitGroup> findAll(Specification<SplitGroup> spec, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortOrder), sortBy);
        return jpaRepository.findAll(sort).stream()
                .map(persistenceMapper::toDomain)
                .toList();
    }

    @Override
    public SplitGroup save(SplitGroup splitGroup) {
        SplitGroupEntity entity = persistenceMapper.toEntity(splitGroup);
        SplitGroupEntity savedEntity = jpaRepository.save(entity);
        return persistenceMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByIdAndUserLogin(Long id, String login) {
        return jpaRepository.existsByIdAndUsers_Login(id, login);
    }
}
