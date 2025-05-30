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

    public UserAccess saveUser(UserAccess userAccess) {
        return userRepository.save(userAccess);
    }

    public Optional<UserAccess> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserAccess> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserAccess> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}