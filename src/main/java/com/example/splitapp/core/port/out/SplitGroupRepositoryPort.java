package com.example.splitapp.core.port.out;

import com.example.splitapp.core.domain.SplitGroup;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface SplitGroupRepositoryPort {

    Optional<SplitGroup> findById(Long id);

    List<SplitGroup> findAll(Specification<SplitGroup> spec, String sortBy, String sortOrder);

    SplitGroup save(SplitGroup splitGroup);

    void deleteById(Long id);

    boolean existsByIdAndUserLogin(Long id, String login);
}
