package com.example.subtrack.repository;

import com.example.subtrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByIdAndIsActive(UUID id, Boolean isActive);
}