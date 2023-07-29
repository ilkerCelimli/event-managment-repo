package com.portifolyo.userservice.config;

import com.portifolyo.userservice.security.AccessDeniedErrorPoint;
import com.portifolyo.userservice.security.CustomAuthenticationEntryPoint;
import com.portifolyo.userservice.security.JwtFilter;
import com.portifolyo.userservice.security.RequestLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    public SecurityFilterChain filterChain(HttpSecurity http, CustomAuthenticationEntryPoint authenticationEntryPoint,
                                           JwtFilter jwtFilter,
                                           AccessDeniedErrorPoint accessDeniedErrorPoint,
                                           RequestLogFilter requestLogFilter) throws Exception {
        http.csrf().disable();
        http.sessionManagement(i -> i.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(i ->i.requestMatchers("/user/login","user/register","/user/refresh","/user/activeuser","/actuator/metrics","/user/addrole").permitAll()
                .requestMatchers(HttpMethod.GET,"/user/finduser",
                        "/").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/").hasRole("ADMIN")
                .anyRequest().authenticated());
        http.exceptionHandling(i ->{
            i.authenticationEntryPoint(authenticationEntryPoint);
            i.accessDeniedHandler(accessDeniedErrorPoint);
                });
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(requestLogFilter, JwtFilter.class);
        return http.build();
    }


}
