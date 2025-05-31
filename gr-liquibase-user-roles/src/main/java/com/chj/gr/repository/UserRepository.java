package com.chj.gr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chj.gr.entity.UserAccess;

public interface UserRepository extends JpaRepository<UserAccess, Long> {
	
    Optional<UserAccess> findByUsername(String username);
    
    @Query("SELECT u FROM UserAccess u INNER JOIN FETCH u.roles WHERE u.username = ?1")
    UserAccess findByUsernameQuestionMark(String username);

    /**
     * Alternative with named parameter for clarity
     */
    @Query("SELECT u FROM UserAccess u INNER JOIN FETCH u.roles WHERE u.username = :username")
    UserAccess findByUsernameNamed(@Param("username") String username);
    
    @Query("SELECT u FROM UserAccess u INNER JOIN FETCH u.roles")
    List<UserAccess> findAllWithRoles();
}