package com.gspatace.blizzard.swagger.integration.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Empty configuration method override in order to
     * disable Spring`s Basic Authentication
     *
     * @param http Not used.
     *             It allows configuration of web security for all requests
     */
    @Override
    protected void configure(HttpSecurity http) {
        /**
         * Override to disable Spring Basic Auth
         */
    }
}
