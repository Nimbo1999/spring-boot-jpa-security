package io.github.nimbo1999.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.nimbo1999.domain.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}
