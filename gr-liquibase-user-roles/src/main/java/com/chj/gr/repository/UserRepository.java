package com.chj.gr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chj.gr.entity.UserAccess;

public interface UserRepository extends JpaRepository<UserAccess, Long> {
    Optional<UserAccess> findByUsername(String username);
}