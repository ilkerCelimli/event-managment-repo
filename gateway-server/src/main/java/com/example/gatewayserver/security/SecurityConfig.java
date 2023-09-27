package com.example.gatewayserver.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, SecuredEndPoints endPoints, Roles roles) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        http.authorizeExchange(request -> request.pathMatchers(endPoints.getPermittedEndPoint())
                .permitAll());
        http.authorizeExchange(request -> request.pathMatchers(endPoints.getAdminEndPoints())
                .hasAnyAuthority(roles.getSuperAdminRoles()));
        return http.build();
    }


}
