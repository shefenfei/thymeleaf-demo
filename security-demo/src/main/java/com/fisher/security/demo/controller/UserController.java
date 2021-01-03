package com.fisher.security.demo.controller;

import com.fisher.security.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authenticationToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String token = jwtUtil.generateToken(userDetails);
            HashMap<String, String> hashMap = new HashMap<>(10);
            hashMap.put("jwt", token);
            return ResponseEntity.ok(hashMap);
        } catch (AuthenticationException e) {
            logger.error("用户名密码错误");
        }
        return ResponseEntity.ok("");
    }


    @GetMapping("/index")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok("{\n" +
                "  \"success\": \"ok\",\n" +
                "  \"message\": \"这是验证过jwt的返回\",\n" +
                "  \"code\": 0\n" +
                "}");
    }

    @GetMapping("/detail/{id}")
    public Map<String, Object> index(@PathVariable("id") Long id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", "0");
        map.put("message", "success");
        return map;
    }
}
