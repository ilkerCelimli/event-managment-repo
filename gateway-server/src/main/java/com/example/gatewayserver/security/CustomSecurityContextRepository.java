package com.example.gatewayserver.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomSecurityContextRepository implements ServerSecurityContextRepository {

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;

    public CustomSecurityContextRepository(ReactiveAuthenticationManager reactiveAuthenticationManager) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
       throw new UnsupportedOperationException();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        ServerHttpRequest request = swe.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String refreshHeader = request.getHeaders().getFirst("Refresh_Token");
        String authToken = null;
        String refreshToken = refreshHeader != null ? refreshHeader.replace("Bearer ","") : "";
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authToken = authHeader.replace("Bearer ", "");
        }
        if (authToken != null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, refreshToken);
            return this.reactiveAuthenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        } else {
            return Mono.empty();
        }
    }
}
