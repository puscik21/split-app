package com.example.splitapp.user.adapter.out.persistence.jpa;

import com.example.splitapp.user.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByLogin(String login);

    void deleteByLogin(String login);

    List<UserEntity> findAllByLoginContainingIgnoreCase(String loginFilter, Sort sort);
}
