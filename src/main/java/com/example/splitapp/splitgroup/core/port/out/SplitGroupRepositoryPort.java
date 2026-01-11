package com.example.splitapp.splitgroup.core.port.out;

import com.example.splitapp.splitgroup.core.domain.model.SplitGroup;

import java.util.List;
import java.util.Optional;

public interface SplitGroupRepositoryPort {

    Optional<SplitGroup> findById(Long id);

    List<SplitGroup> findAll(String sortBy, String sortOrder);

    List<SplitGroup> findByUserLogin(String login);

    SplitGroup save(SplitGroup splitGroup);

    void deleteById(Long id);

    boolean existsByIdAndUserLogin(Long id, String login);
}
