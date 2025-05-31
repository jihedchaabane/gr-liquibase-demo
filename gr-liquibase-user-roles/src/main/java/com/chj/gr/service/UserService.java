package com.chj.gr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chj.gr.entity.UserAccess;
import com.chj.gr.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserAccess> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserAccess> findByUsernameQuestionMark(String username) {
        return Optional.ofNullable(userRepository.findByUsernameQuestionMark(username));
    }

    public Optional<UserAccess> findByUsernameNamed(String username) {
        return Optional.ofNullable(userRepository.findByUsernameNamed(username));
    }

    public List<UserAccess> findAllUsers() {
        return userRepository.findAll();
    }
    
    public List<UserAccess> findAllWithRoles() {
        return userRepository.findAllWithRoles(); // Custom query with JOIN FETCH
    }
}