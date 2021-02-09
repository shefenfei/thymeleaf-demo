package com.fisher.demo.gateway.config;

import com.google.gson.Gson;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 自定义全局过滤器，所有的请求会先过这个过滤器
 * @author shefenfei
 */
@Configuration
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private static final String[] excludePaths = {"/lesson/**", "/demo/getDict", "/demo/save1", "/users/**"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response=exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path1 = request.getPath();
        String value = path1.value();
        if (!buildExcludePaths(excludePaths).contains(value)) {
            return this.setErrorResponse(response,"需要登录");
        }
        System.out.println(value);
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String authorization=headers.getFirst("Authorization");
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


    private List<String> buildExcludePaths(String[] excludePaths) {
        ArrayList<String> paths = new ArrayList<>();
        if (excludePaths.length == 0) {
            return Collections.EMPTY_LIST;
        }
        for (String excludePath : excludePaths) {
            paths.add("/school" + excludePath);
        }
        return paths;
    }
}
