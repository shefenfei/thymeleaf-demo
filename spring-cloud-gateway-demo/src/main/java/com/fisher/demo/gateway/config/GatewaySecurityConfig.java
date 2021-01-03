package com.fisher.demo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {


    private static final String[] excludePaths = {"/lesson/**", "/login?error"};

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.authorizeExchange()
                .pathMatchers(excludePaths).permitAll()
                .anyExchange().authenticated()
                .and().csrf(csrf -> csrf.disable())
                .cors(corsSpec -> {
                    corsSpec.configurationSource(serverWebExchange -> {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.addAllowedOrigin("*");
                        return corsConfiguration;
                    });
                });
//                .formLogin().and()
//                .exceptionHandling().authenticationEntryPoint(new CustomServerAuthenticationEntryPoint("/login"));
        return httpSecurity.build();
    }

}
