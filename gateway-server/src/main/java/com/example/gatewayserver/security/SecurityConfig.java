package com.example.gatewayserver.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                                      SecuredEndPoints endPoints,
                                                      Roles roles,
                                                      CustomSecurityContextRepository jwtFilter,
                                                      RequestLogFilter requestLogFilter,
                                                      CustomAuthenticationManager customAuthenticationManager,
                                                      CustomAuthenticationEntryPoint authenticationEntryPoint,
                                                      AccessDeniedErrorPoint accessDeniedErrorPoint
                                                      ) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        http.securityContextRepository(jwtFilter);
        http.authenticationManager(customAuthenticationManager);
        http.exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedErrorPoint));
        http.exceptionHandling(handler -> handler.authenticationEntryPoint(authenticationEntryPoint));
        http.authorizeExchange(request -> request.pathMatchers(endPoints.getPermittedEndPoint())
                .permitAll());
        http.authorizeExchange(request -> request.pathMatchers(endPoints.getAdminEndPoints())
                .hasAnyAuthority(roles.getSuperAdminRoles()));
        http.addFilterBefore(requestLogFilter, SecurityWebFiltersOrder.FIRST);
        return http.build();
    }


}
