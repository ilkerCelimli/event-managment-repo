package com.portifolyo.eventservice.config;

import com.portifolyo.eventservice.security.AccessDeniedErrorPoint;
import com.portifolyo.eventservice.security.CustomAuthenticationEntryPoint;
import com.portifolyo.eventservice.security.JwtFilter;
import com.portifolyo.eventservice.security.RequestLogFilter;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   JwtFilter jwtFilter,
                                                   CustomAuthenticationEntryPoint authenticationEntryPoint,
                                                   AccessDeniedErrorPoint accessDeniedErrorPoint,
                                                   RequestLogFilter requestLogFilter) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeHttpRequests(i -> {
            i.requestMatchers(HttpMethod.GET, "/event/").permitAll();
            i.requestMatchers("/actuator/**").permitAll();
            i.requestMatchers("/ticket/**").hasRole("USER");
            i.requestMatchers(HttpMethod.PUT, "/organizator/").hasRole("USER");
            i.requestMatchers(HttpMethod.POST, "/organizator/").hasRole("USER");
            i.requestMatchers("/organizator/findorganizarevents", "/organizator/findorganizatorbyemail").permitAll();
            i.requestMatchers("/event/").hasRole("USER");
        });
        httpSecurity.exceptionHandling(i -> {
            i.accessDeniedHandler(accessDeniedErrorPoint);
            i.authenticationEntryPoint(authenticationEntryPoint);
        });
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(requestLogFilter, JwtFilter.class);
        return httpSecurity.build();
    }

}
