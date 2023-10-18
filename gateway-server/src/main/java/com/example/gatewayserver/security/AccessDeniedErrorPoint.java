package com.example.gatewayserver.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class AccessDeniedErrorPoint implements ServerAccessDeniedHandler {


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        exchange.getResponse().getHeaders().add("Content-Type","application/json");
        GenericResponse<Void> error = new GenericResponse<>(403,"Access Denied");
        ObjectMapper mapper = new ObjectMapper();
        String errorMessage = null;
        try {
            errorMessage = mapper.writeValueAsString(error);
        } catch (JsonProcessingException e) {
            return Mono.empty();
        }
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory()
                .wrap(errorMessage.getBytes())));
    }
}
