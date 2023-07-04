package com.portifolyo.gatewayserver.security;

import com.portifolyo.gatewayserver.security.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
public class ConfigSecurity {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, AuthFilter authFilter){
        http.csrf().disable();
        http.authorizeExchange()
                .pathMatchers("/user/registerUser","/user/login","user/refresh")
                .permitAll()
                .anyExchange().authenticated();

        http.addFilterBefore(authFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

}
