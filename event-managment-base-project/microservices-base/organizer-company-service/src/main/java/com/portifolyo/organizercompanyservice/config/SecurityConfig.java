package com.portifolyo.organizercompanyservice.config;

import com.portifolyo.organizercompanyservice.security.AccessDeniedErrorPoint;
import com.portifolyo.organizercompanyservice.security.CustomAuthenticationEntryPoint;
import com.portifolyo.organizercompanyservice.security.JwtFilter;
import com.portifolyo.organizercompanyservice.security.RequestLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AccessDeniedErrorPoint accessDeniedErrorPoint,
                                                   CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                                   JwtFilter jwtFilter,
                                                   RequestLogFilter requestLogFilter) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE,"/api/v1/company/delete-company/{id}").permitAll());
        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET,"/**/*/company/").permitAll());
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(customAuthenticationEntryPoint);
            exception.accessDeniedHandler(accessDeniedErrorPoint);
        });
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(requestLogFilter, JwtFilter.class);
        return http.build();

    }
}
