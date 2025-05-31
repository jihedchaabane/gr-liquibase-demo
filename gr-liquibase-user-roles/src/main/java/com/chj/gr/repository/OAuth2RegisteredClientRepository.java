package com.chj.gr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chj.gr.dtos.OAuth2RegisteredClientProjection;
import com.chj.gr.entity.OAuth2RegisteredClient;

public interface OAuth2RegisteredClientRepository extends JpaRepository<OAuth2RegisteredClient, String> {
	@Query("SELECT "
			+ "c.id AS id, "
			+ "c.clientId AS clientId, "
			+ "c.clientSecret AS clientSecret, "
			+ "c.scopes AS scopes, "
			+ "c.clientName AS clientName, "
			+ "c.authorizationGrantTypes AS authorizationGrantTypes, "
		    + "c.clientAuthenticationMethods AS clientAuthenticationMethods, "
		    + "c.clientIdIssuedAt AS clientIdIssuedAt, "
		    + "c.tokenSettings AS tokenSettings, "
		    + "c.clientSettings AS clientSettings, "
		    + "c.clientSecretExpiresAt AS clientSecretExpiresAt, "
		    + "c.redirectUris AS redirectUris, "
		    + "c.postLogoutRedirectUris AS postLogoutRedirectUris "
			+ "FROM OAuth2RegisteredClient c " 
			+ "ORDER BY c.clientIdIssuedAt"
	)
	List<OAuth2RegisteredClientProjection> findClientDetails();
	
	@Query("SELECT "
			+ "c.id AS id, "
			+ "c.clientId AS clientId, "
			+ "c.scopes AS scopes, "
			+ "c.clientName AS clientName, "
			+ "c.authorizationGrantTypes AS authorizationGrantTypes, "
		    + "c.clientAuthenticationMethods AS clientAuthenticationMethods, "
		    + "c.clientIdIssuedAt AS clientIdIssuedAt "
			+ "FROM OAuth2RegisteredClient c " 
			+ "ORDER BY c.clientIdIssuedAt"
	)
	List<OAuth2RegisteredClientProjection> findClientDetailsSomeInfos();
	
	    
}