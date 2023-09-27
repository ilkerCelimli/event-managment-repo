package com.example.gatewayserver.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Component
public class RequestLogFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String adress = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName();
        String method = exchange.getRequest().getMethod().toString();
        String date = LocalDateTime.now().toString();
        String path = exchange.getRequest().getPath().toString();
        log.info("Requested {}, {} method {} path {}  ",adress,method,path,date);
        return chain.filter(exchange);
    }
}
