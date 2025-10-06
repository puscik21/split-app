package com.example.splitapp.adapter.out.persistence.jpa;

import com.example.splitapp.adapter.out.persistence.entity.SplitGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSplitGroupRepository extends JpaRepository<SplitGroupEntity, Long>, JpaSpecificationExecutor<SplitGroupEntity> {

    boolean existsByIdAndUsers_Login(Long groupId, String login);
}
