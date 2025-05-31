package com.chj.gr.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chj.gr.dtos.OAuth2RegisteredClientProjection;
import com.chj.gr.repository.OAuth2RegisteredClientRepository;

@Service
public class OAuth2ClientQueryJPAService {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2ClientQueryJPAService.class);
    private final OAuth2RegisteredClientRepository auth2RegisteredClientRepository;

    public OAuth2ClientQueryJPAService(OAuth2RegisteredClientRepository auth2RegisteredClientRepository) {
        this.auth2RegisteredClientRepository = auth2RegisteredClientRepository;
    }

    public List<OAuth2RegisteredClientProjection> displayOAuth2Clients() {
    	List<OAuth2RegisteredClientProjection> results = null;
        try {
            results = auth2RegisteredClientRepository.findClientDetails();
            if (results.isEmpty()) {
                logger.info("No OAuth2 clients found in liquibase_oauth2_registered_client.");
                return null;
            }
            logger.info("OAuth2 Clients:");
            for (OAuth2RegisteredClientProjection client : results) {
                logger.info("ID: {}, "
                		+ "ClientId: {}, "
                		+ "ClientSecret: {}, "
                		+ "Scopes: {}, "
                		+ "ClientName: {}, "
                		+ "AuthorizationGrantTypes: {} "
                		+ "ClientAuthenticationMethods: {} "
                		+ "ClientIdIssuedAt: {}"
                		+ "TokenSettings: {} "
                		+ "ClientSettings: {} "
                		+ "ClientSecretExpiresAt: {} "
                		+ "RedirectUris: {} "
                		+ "PostLogoutRedirectUris: {}",
                        client.getId(),
                        client.getClientId(),
                        client.getClientSecret(),
                        client.getScopes(),
                        client.getClientName(),
                        client.getAuthorizationGrantTypes(),
                        client.getClientAuthenticationMethods(),
                        client.getClientIdIssuedAt(),
                		client.getTokenSettings(),
                		client.getClientSettings(),
                		client.getClientSecretExpiresAt(),
                		client.getRedirectUris(),
                		client.getPostLogoutRedirectUris()
                );
            }
        } catch (Exception e) {
            logger.error("Error executing OAuth2 client query: {}", e.getMessage(), e);
        }
        return results;
    }

    public List<OAuth2RegisteredClientProjection> displayOAuth2ClientsSomeInfos() {
    	List<OAuth2RegisteredClientProjection> results = null;
        try {
            results = auth2RegisteredClientRepository.findClientDetailsSomeInfos();
            if (results.isEmpty()) {
                logger.info("No OAuth2 clients found in liquibase_oauth2_registered_client.");
                return null;
            }
            logger.info("OAuth2 Clients:");
            for (OAuth2RegisteredClientProjection client : results) {
                logger.info("ID: {}, "
                		+ "ClientId: {}, "
                		+ "Scopes: {}, "
                		+ "ClientName: {}, "
                		+ "AuthorizationGrantTypes: {} "
                		+ "ClientAuthenticationMethods: {} "
                		+ "ClientIdIssuedAt: {}",
                        client.getId(),
                        client.getClientId(),
                        client.getScopes(),
                        client.getClientName(),
                        client.getAuthorizationGrantTypes(),
                        client.getClientAuthenticationMethods(),
                        client.getClientIdIssuedAt()
                );
            }
        } catch (Exception e) {
            logger.error("Error executing OAuth2 client query: {}", e.getMessage(), e);
        }
        return results;
    }
}