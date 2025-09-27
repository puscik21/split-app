package com.example.splitapp.repository;

import com.example.splitapp.model.SplitGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SplitGroupRepository extends JpaRepository<SplitGroup, Long>, JpaSpecificationExecutor<SplitGroup> {

    Optional<SplitGroup> findByTitleAndDate(String title, LocalDate date);
}
