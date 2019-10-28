package com.fisher.arch.webflux.demo.config;

import com.fisher.arch.webflux.demo.form.UserForm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Configuration
public class WebFluxConfig {


    @Bean
    public RouterFunction routerFunction() {
        StringUtils.hasText("");
        return RouterFunctions.route(RequestPredicates.GET("/hello"),
                serverRequest -> {
                    Optional<String> message = serverRequest.queryParam("message");
                    Mono<UserForm> userFormMono = serverRequest.bodyToMono(UserForm.class);
                    return ServerResponse.ok().body(Mono.just("hello from router"), String.class);
                });
    }
}
