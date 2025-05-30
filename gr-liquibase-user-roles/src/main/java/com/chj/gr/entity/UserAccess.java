package com.chj.gr.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "liquibase_users")
@Data
public class UserAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
    private String email;

	@Column(nullable = false)
	private boolean enabled;
	/**
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "gr_conf_access_user_authorities", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "authority")
	private Set<String> authorities;
	*/
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleAccess> roles;
    	
	@Column(nullable = false)
	private boolean accountNonExpired;
	
	@Column(nullable = false)
	private boolean credentialsNonExpired;
	
	@Column(nullable = false)
	private boolean accountNonLocked;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    
}