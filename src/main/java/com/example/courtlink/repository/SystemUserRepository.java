package com.example.courtlink.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courtlink.entity.SystemUserEntity;

public interface SystemUserRepository  extends JpaRepository<SystemUserEntity, Long> {
    Optional<SystemUserEntity> findByEmail(String email);
}
    

