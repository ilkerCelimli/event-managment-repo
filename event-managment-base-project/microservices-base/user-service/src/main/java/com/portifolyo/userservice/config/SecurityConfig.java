package com.portifolyo.userservice.config;

import com.portifolyo.userservice.security.CustomAuthenticationEntryPoint;
import com.portifolyo.userservice.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomAuthenticationEntryPoint authenticationEntryPoint, JwtFilter jwtFilter) throws Exception {
        http.csrf().disable();
        http.sessionManagement(i -> i.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(i ->i.requestMatchers("/user/login","user/register","user/refresh").permitAll()
                .anyRequest().authenticated());
        http.exceptionHandling(i -> i.authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
