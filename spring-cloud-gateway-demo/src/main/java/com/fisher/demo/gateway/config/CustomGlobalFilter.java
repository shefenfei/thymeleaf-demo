package com.fisher.demo.gateway.config;

import com.google.gson.Gson;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Configuration
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String authorization=headers.getFirst("Authorization");
        ServerHttpResponse response=exchange.getResponse();
        if(authorization == null || ! authorization.startsWith("Bearer ")){
            return this.setErrorResponse(response,"未携带token");
        }
        System.out.println("filter");
        RequestPath path = exchange.getRequest().getPath();
        return chain.filter(exchange);
    }

    protected Mono<Void> setErrorResponse(ServerHttpResponse response, String message){
        Gson gson = new Gson();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> resp = new HashMap<>();
        resp.put("status_code", 500);
        resp.put("data",message);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(gson.toJson(resp).getBytes())));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
