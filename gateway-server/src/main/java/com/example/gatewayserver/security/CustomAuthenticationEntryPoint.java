package com.example.gatewayserver.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        exchange.getResponse().getHeaders().add("Content-Type","application/json");
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        GenericResponse<Void> error = new GenericResponse<>(HttpStatus.UNAUTHORIZED.value(),"Authentication is required");
        String errorMessage;
        try {
            errorMessage = objectMapper.writeValueAsString(error);
        }
        catch (JsonProcessingException exception){
            return Mono.empty();
        }
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory()
                .wrap(errorMessage.getBytes())));
    }
}
