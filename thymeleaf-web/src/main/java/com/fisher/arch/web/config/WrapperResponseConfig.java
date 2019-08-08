package com.fisher.arch.web.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

//@Component
//@ControllerAdvice(basePackages = "com.fisher.arch.web.controller")
public class WrapperResponseConfig implements ResponseBodyAdvice<Map<String, Object>> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Map<String, Object> beforeBodyWrite(Map<String, Object> map,
                                               MethodParameter methodParameter,
                                               MediaType mediaType,
                                               Class<? extends HttpMessageConverter<?>> aClass,
                                               ServerHttpRequest serverHttpRequest,
                                               ServerHttpResponse serverHttpResponse) {
        map.put("changed", true);
        return map;
    }

}
