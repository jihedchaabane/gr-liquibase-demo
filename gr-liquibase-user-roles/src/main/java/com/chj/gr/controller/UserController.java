package com.chj.gr.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chj.gr.entity.UserAccess;
import com.chj.gr.service.UserService;

@RestController
@RequestMapping("/gr-liquibase-user-roles/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserAccess> getUserByUsername(@PathVariable String username) {
    	/**
         * NO issues with : spring.jpa.open-in-view: true (is enabled by default).
         */
    	Optional<UserAccess> optUserAccess = userService.findByUsernameQuestionMark(username);
    	optUserAccess.ifPresent(u -> {
			System.out.println(u.getUsername());
			u.getRoles().forEach(r -> System.out.println("	" + r.getName()));
		});
    	return optUserAccess
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/username/ok")
//    @PostConstruct
    public ResponseEntity<UserAccess> getUserByUsername() {
    	/**
         * NO issues with : spring.jpa.open-in-view: true (is enabled by default).
         */
    	Optional<UserAccess> optUserAccess = userService.findByUsernameNamed("none");
    	optUserAccess.ifPresent(u -> {
			System.out.println(u.getUsername());
			u.getRoles().forEach(r -> System.out.println("	" + r.getName()));
		});
    	/**
    	 * findByUsernameQuestionMark.
    	 */
    	optUserAccess = userService.findByUsernameQuestionMark("admin");
    	optUserAccess.ifPresent(u -> {
			System.out.println(u.getUsername());
			u.getRoles().forEach(r -> System.out.println("	" + r.getName()));
		});
    	/**
    	 * findByUsernameNamed.
    	 */
    	optUserAccess = userService.findByUsernameNamed("admin");
    	optUserAccess.ifPresent(u -> {
			System.out.println(u.getUsername());
			u.getRoles().forEach(r -> System.out.println("	" + r.getName()));
		});
    	
    	ResponseEntity<UserAccess> responseEntity = optUserAccess
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    	return responseEntity;
    }
    
    @GetMapping("/username/ko")
//    @PostConstruct
    public ResponseEntity<UserAccess> getUserByUsernameKo() {
    	/**
         * There should be an issue when : spring.jpa.open-in-view: true (is enabled by default).
         * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.chj.gr.entity.UserAccess.roles, 
         * 		could not initialize proxy - no Session
         */
    	Optional<UserAccess> optUserAccess = userService.findUserByUsername("admin");
    	optUserAccess.ifPresent(u -> {
			System.out.println(u.getUsername());
			u.getRoles().forEach(r -> System.out.println("	" + r.getName()));
		});
    	return optUserAccess
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
//    @PostConstruct
    public ResponseEntity<List<UserAccess>> getAllUsers() {
    	/**
         * There should be an issue when : spring.jpa.open-in-view: true (is enabled by default).
         * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.chj.gr.entity.UserAccess.roles, 
         * 		could not initialize proxy - no Session
         */
    	List<UserAccess> list = userService.findAllUsers();
    	list.stream().forEach(
    			u -> {
    				System.out.println(u.getUsername());
    				u.getRoles().forEach(r -> System.out.println("	" + r.getName()));
    			}
    	);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/users")
//    @PostConstruct
    public ResponseEntity<List<UserAccess>> getUsers() {
    	/**
         * NO issues with : spring.jpa.open-in-view: true (is enabled by default).
         */
    	List<UserAccess> list = userService.findAllWithRoles(); // Custom query with JOIN FETCH
    	list.stream().forEach(
    			u -> {
    				System.out.println(u.getUsername());
    				u.getRoles().forEach(r -> System.out.println("	" + r.getName()));
    			}
    	);
        return ResponseEntity.ok(list);
    }
}