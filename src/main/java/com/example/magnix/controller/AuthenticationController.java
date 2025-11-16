package com.example.magnix.controller;

import com.example.magnix.dto.LoginRequest;
import com.example.magnix.dto.LoginResponse;
import com.example.magnix.model.User;
import com.example.magnix.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        return new LoginResponse(user.getEmail(), user.getRole());
    }
}
