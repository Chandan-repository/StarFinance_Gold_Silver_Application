package com.java.stargold.authenticationservice.controller;

import com.java.stargold.authenticationservice.entity.User;
import com.java.stargold.authenticationservice.security.JwtUtil;
import com.java.stargold.authenticationservice.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {
        User user = authService.findByUsername(req.get("username"));
        // Password check simplified (you can use AuthenticationManager)
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return Map.of("token", token);
    }
}
