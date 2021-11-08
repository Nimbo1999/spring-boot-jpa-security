package io.github.nimbo1999.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.nimbo1999.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
