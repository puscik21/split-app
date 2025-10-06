package com.example.splitapp.adapter.out.persistence.jpa;

import com.example.splitapp.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// TODO: Do I need JpaSpecificationExecutor here? - probably no - No Specification class used
public interface JpaUserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByLogin(String login);

    void deleteByLogin(String login);

    List<UserEntity> findAllByLoginContainingIgnoreCase(String loginFilter, Sort sort);
}
