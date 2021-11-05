package io.github.nimbo1999.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.nimbo1999.domain.entity.PhoneNumber;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}
