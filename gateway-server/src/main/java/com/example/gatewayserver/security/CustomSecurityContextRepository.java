package com.example.gatewayserver.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.gatewayserver.model.SecurityModel;
import org.portifolyo.utils.JsonTokenUtils;
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
        SecurityModel securityModel = null;
        if (authToken != null) {
            DecodedJWT token = JsonTokenUtils.decodeJWT(authToken);
            if(token != null) {
             securityModel = new SecurityModel(
                     token.getClaim("id").asString(),
                     token.getClaim("ip").asString(),
                     token.getClaim("email").asString(),
                     token.getClaim("roles").asList(String.class),
                     authToken,
                     refreshToken,
                     token.getClaim("type").asString()
             );
            }
            else {
                DecodedJWT refresh = JsonTokenUtils.decodeJWT(refreshToken);
                securityModel = new SecurityModel(
                        refresh.getClaim("id").asString(),
                        refresh.getClaim("ip").asString(),
                        refresh.getClaim("email").asString(),
                        refresh.getClaim("roles").asList(String.class),
                        null,
                        refreshToken,
                        token.getClaim("type").asString());
                if(!swe.getRequest().getLocalAddress().getAddress().getHostAddress().equals(refresh.getClaim("ip").asString())){
                    return Mono.empty();
                }
            }
                Authentication auth = new UsernamePasswordAuthenticationToken(securityModel,null);
                return this.reactiveAuthenticationManager.authenticate(auth).map(SecurityContextImpl::new);

        } else {
            return Mono.empty();
        }
    }
}
