package com.example.splitapp.repository;

import com.example.splitapp.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    List<User> findAllByLoginContainingIgnoreCase(String loginFilter, Sort sort);
}
