package com.example.splitapp.user.adapter.out.persistence;

import com.example.splitapp.user.adapter.out.persistence.entity.UserEntity;
import com.example.splitapp.user.adapter.out.persistence.jpa.JpaUserRepository;
import com.example.splitapp.user.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.example.splitapp.user.core.domain.model.User;
import com.example.splitapp.user.core.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaRepository;
    private final UserPersistenceMapper persistenceMapper;

    @Override
    public Optional<User> findByLogin(String login) {
        return jpaRepository.findByLogin(login).map(persistenceMapper::toDomain);
    }

    @Override
    public List<User> findAll(String loginFilter, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortOrder), sortBy);
        List<UserEntity> entities;
        if (StringUtils.hasText(loginFilter)) {
            entities = jpaRepository.findAllByLoginContainingIgnoreCase(loginFilter, sort);
        } else {
            entities = jpaRepository.findAll(sort);
        }
        return entities.stream().map(persistenceMapper::toDomain).toList();
    }

    @Override
    public User save(User user) {
        UserEntity entity = persistenceMapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.save(entity);
        return persistenceMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteByLogin(String login) {
        jpaRepository.deleteByLogin(login);
    }
}
