package com.chj.gr.dtos;

import java.time.LocalDateTime;
/**
 * spring.jpa.open-in-view is enabled by default. 
 * Therefore, database queries may be performed during view rendering. 
 * Explicitly configure spring.jpa.open-in-view to disable this warning:
 * 
 * It indicates that the Spring Boot property spring.jpa.open-in-view is enabled by default (set to true), 
 * 		which keeps the JPA EntityManager open during the entire HTTP request, including view rendering.
 * 		This can lead to database queries being executed unexpectedly in the view layer (e.g., when accessing lazy-loaded relationships in a Thymeleaf template or JSON serialization), 
 * 		potentially causing performance issues or LazyInitializationException errors.
 * 
 * To address the warning, you need to explicitly disable spring.jpa.open-in-view by setting it to false in application.yml configuration.
 * 		This ensures the EntityManager is closed after the service layer, preventing unintended database queries during view rendering.
 * 
 * Disabling spring.jpa.open-in-view may expose issues in applications if it relies on lazy-loaded relationships being accessed in the view layer:
 *  (e.g., liquibase_oauth2_registered_client, OAuth2ClientQueryJPAService, and JPA entities like UserAccess and OAuth2RegisteredClient).
 *  
 * Common Issue: LazyInitializationException:
 * When spring.jpa.open-in-view is disabled, accessing lazy-loaded relationships outside the service layer
 * 		(e.g., UserAccess.roles with @ManyToMany(fetch = FetchType.LAZY))  
 * 		(e.g., in a controller or view) 
 * 		===> will throw a LazyInitializationException because the EntityManager is closed.
 * 
 * Solutions:
 * 		- Modify your queries to fetch required relationships upfront using JOIN FETCH or @EntityGraph.
 * 		-*** DTOs or JPA Projections : to load only the needed data, avoiding lazy-loaded relationships
 * 		- Fetch in Service Layer (Not Recommended ==> may impact performance for large datasets. 
 * 									Consider switching to FetchType.LAZY and using JOIN FETCH or @EntityGraph in specific queries
 *								 ):
 * 				-- Load all necessary data in the service layer before passing to the controller or view.
 * 				-- Example: For UserAccess, fetch roles eagerly if needed
 * 
 * Disabling spring.jpa.open-in-view is recommended for most applications to improve performance
 * 		and enforce explicit data fetching.
 */
public interface OAuth2RegisteredClientProjection {
    String getId();
    String getClientId();
    String getClientSecret();
    String getClientName();
    String getScopes();
    String getAuthorizationGrantTypes();
    String getClientAuthenticationMethods();
    LocalDateTime getClientIdIssuedAt();
    String getTokenSettings();
    String getClientSettings();
    LocalDateTime getClientSecretExpiresAt();
    String getRedirectUris();
    String getPostLogoutRedirectUris();
}