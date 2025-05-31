package com.chj.gr.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chj.gr.dtos.OAuth2RegisteredClientProjection;
import com.chj.gr.entity.OAuth2RegisteredClient;
import com.chj.gr.service.OAuth2ClientQueryJPAService;
import com.chj.gr.service.OAuth2ClientQueryJdbcTemplateService;

@RestController
@RequestMapping("/gr-liquibase-user-roles/oauth2clients")
public class Oauth2RegisteredClientController {
	
    private final OAuth2ClientQueryJPAService oAuth2ClientQueryJPAService;
    private final OAuth2ClientQueryJdbcTemplateService oAuth2ClientQueryJdbcTemplateService;

    public Oauth2RegisteredClientController(
    		OAuth2ClientQueryJPAService oAuth2ClientQueryJPAService,
    		OAuth2ClientQueryJdbcTemplateService oAuth2ClientQueryJdbcTemplateService) {
        this.oAuth2ClientQueryJPAService = oAuth2ClientQueryJPAService;
        this.oAuth2ClientQueryJdbcTemplateService = oAuth2ClientQueryJdbcTemplateService;
    }

    @GetMapping("/allInfos")
//    @PreAuthorize("hasAuthority('SCOPE_admin.all.read')")
    public ResponseEntity<List<OAuth2RegisteredClientProjection>> allInfos() {
    	/**
         * NO issues with : spring.jpa.open-in-view: true (is enabled by default).
         */
        return ResponseEntity.ok(oAuth2ClientQueryJPAService.displayOAuth2Clients());
    }

    @GetMapping("/someInfos")
//  @PreAuthorize("hasAuthority('SCOPE_admin.some.read')")
    public ResponseEntity<List<OAuth2RegisteredClientProjection>> someInfos() {
    	/**
         * NO issues with : spring.jpa.open-in-view: true (is enabled by default).
         */
        return ResponseEntity.ok(oAuth2ClientQueryJPAService.displayOAuth2Clients());
    }

    @GetMapping("jdbcTemplate/someInfos")
//  @PreAuthorize("hasAuthority('SCOPE_admin.some.read')")
    public ResponseEntity<List<OAuth2RegisteredClient>> jdbcTemplateSomeInfos() {
    	/**
         * NO issues with : spring.jpa.open-in-view: true (is enabled by default).
         */
        return ResponseEntity.ok(oAuth2ClientQueryJdbcTemplateService.displayOAuth2Clients());
    }
}