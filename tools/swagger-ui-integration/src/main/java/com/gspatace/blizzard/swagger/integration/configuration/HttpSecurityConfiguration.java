package com.gspatace.blizzard.swagger.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class HttpSecurityConfiguration {

    /**
     * Empty bean configuration method in order to
     * disable Spring`s Basic Authentication
     *
     * @param http Not used.
     *             It allows configuration of web security for all requests
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        return http.build();
    }
}
