package com.portifolyo.gatewayserver.security.filter;

import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

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
           Authentication authentication = new UsernamePasswordAuthenticationToken("user",null, List.of(new SimpleGrantedAuthority("user")));
           SecurityContextHolder.getContext().setAuthentication(authentication);
       return chain.filter(exchange);

       }
           return Mono.error(new AuthenticationException());
    }
}
