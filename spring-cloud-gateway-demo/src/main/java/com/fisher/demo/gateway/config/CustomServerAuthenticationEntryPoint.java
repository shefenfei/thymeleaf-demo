package com.fisher.demo.gateway.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class CustomServerAuthenticationEntryPoint extends RedirectServerAuthenticationEntryPoint {

    public CustomServerAuthenticationEntryPoint(String location) {
        super(location);
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return super.commence(exchange, e);
    }
}
