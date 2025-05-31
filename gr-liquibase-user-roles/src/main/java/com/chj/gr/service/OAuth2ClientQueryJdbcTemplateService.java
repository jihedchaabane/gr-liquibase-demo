package com.chj.gr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.chj.gr.entity.OAuth2RegisteredClient;

@Service
public class OAuth2ClientQueryJdbcTemplateService {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2ClientQueryJdbcTemplateService.class);
    private final JdbcTemplate jdbcTemplate;

    public OAuth2ClientQueryJdbcTemplateService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OAuth2RegisteredClient> displayOAuth2Clients() {
    	
    	List<OAuth2RegisteredClient> results = null;
    	
        String sql = "SELECT id, client_id, scopes, client_name " +
                     "FROM liquibase_oauth2_registered_client " +
                     "ORDER BY client_id_issued_at";
        try {
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
            if (list.isEmpty()) {
                logger.info("No OAuth2 clients found in liquibase_oauth2_registered_client.");
            } else {
            	results = new ArrayList<>();
            }
            logger.info("OAuth2 Clients:");
            for (Map<String, Object> row : list) {
                logger.info("ID: {}, Client ID: {}, Scopes: {}, Client Name: {}",
                        row.get("id"),
                        row.get("client_id"),
                        row.get("scopes"),
                        row.get("client_name"));
                results.add(new OAuth2RegisteredClient(
                		(String) row.get("id"), 
                		(String) row.get("client_id"),
                		(String) row.get("scopes"),
                		(String) row.get("client_name")));
            }
        } catch (Exception e) {
            logger.error("Error executing OAuth2 client query: {}", e.getMessage(), e);
        }
        return results;
    }
}