package com.example.splitapp.splitgroup.adapter.out.persistence.jpa;

import com.example.splitapp.splitgroup.adapter.out.persistence.entity.SplitGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSplitGroupRepository extends JpaRepository<SplitGroupEntity, Long> {

    boolean existsByIdAndUsers_Login(Long groupId, String login);

    List<SplitGroupEntity> findAllByUsers_Login(String login);
}
