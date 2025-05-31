package com.chj.gr.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "liquibase_oauth2_registered_client")
@Data
@ToString
public class OAuth2RegisteredClient {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "client_secret", nullable = false, unique = true)
    private String clientSecret;

    @Column(name = "scopes", nullable = false)
    private String scopes;

    @Column(name = "client_name", nullable = false, unique = true)
    private String clientName;

    @Column(name = "authorization_grant_types", nullable = false)
    private String authorizationGrantTypes;

    @Column(name = "client_authentication_methods", nullable = false)
    private String clientAuthenticationMethods;

    @Column(name = "client_id_issued_at", nullable = false)
    private LocalDateTime clientIdIssuedAt;

    @Column(name = "token_settings", nullable = false)
    private String tokenSettings;

    @Column(name = "client_settings", nullable = false)
    private String clientSettings;

    @Column(name = "client_secret_expires_at")
    private LocalDateTime clientSecretExpiresAt;

    @Column(name = "redirect_uris")
    private String redirectUris;

    @Column(name = "post_logout_redirect_uris")
    private String postLogoutRedirectUris;

	public OAuth2RegisteredClient(String id, String clientId, String scopes, String clientName) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.scopes = scopes;
		this.clientName = clientName;
	}
    
    
}