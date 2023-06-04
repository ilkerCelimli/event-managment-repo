package com.portifolyo.gatewayserver.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;
@Component
public class AuthFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
       HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
       if(exchange.getRequest().getPath().toString().contains("register") ||
               exchange.getRequest().getPath().toString().contains("login") ||
               exchange.getRequest().getPath().toString().contains("refresh")){
          return chain.filter(exchange);
       }
       if(httpHeaders.get(HttpHeaders.AUTHORIZATION) != null){
       return chain.filter(exchange);

       }
           return Mono.error(new AuthenticationException());
    }
}
