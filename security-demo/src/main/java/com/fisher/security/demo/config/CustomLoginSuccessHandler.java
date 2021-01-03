package com.fisher.security.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write("{\n" +
                "  \"data\": null,\n" +
                "  \"message\": \"登录成功\",\n" +
                "  \"code\": 0\n" +
                "}");
    }
}
