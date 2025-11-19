package com.example.magnix.controller;

import com.example.magnix.dto.LoginRequest;
import com.example.magnix.dto.LoginResponse;
import com.example.magnix.model.User;
import com.example.magnix.security.JwtUtil;
import com.example.magnix.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());

        // Generar JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new LoginResponse(user.getEmail(), user.getRole(), token);
    }
}
