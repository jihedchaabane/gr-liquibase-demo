package com.chj.gr.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "liquibase_persistent_logins")
@Data
public class PersistentLogin {
	@Id
	@Column(length = 64)
	private String series;

	@Column(length = 64, nullable = false)
	private String username;

	@Column(length = 64, nullable = false)
	private String token;

	@Column(nullable = false)
	private LocalDateTime lastUsed;
}
