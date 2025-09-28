package com.example.splitapp.repository;

import com.example.splitapp.model.SplitGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitGroupRepository extends JpaRepository<SplitGroup, Long>, JpaSpecificationExecutor<SplitGroup> {

    boolean existsByIdAndUsers_Login(Long groupId, String login);
}
