package com.portifolyo.userservice.config;

import com.portifolyo.userservice.security.RequestLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           RequestLogFilter requestLogFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(i -> i.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(reqeest -> reqeest.anyRequest().permitAll());
        http.addFilterBefore(requestLogFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public CorsConfiguration corsConfiguration(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("*");
        configuration.addAllowedOriginPattern("*");
        return configuration;
    }


}
