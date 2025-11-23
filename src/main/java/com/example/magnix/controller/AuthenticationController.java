package com.example.magnix.controller;

import com.example.magnix.dto.LoginRequest;
import com.example.magnix.dto.LoginResponse;
import com.example.magnix.dto.LoginResponse.UserDto;
import com.example.magnix.dto.RegisterRequest;
import com.example.magnix.model.User;
import com.example.magnix.security.JwtUtil;
import com.example.magnix.service.AuthenticationService;

import org.springframework.http.ResponseEntity;
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

        // Crear el objeto UserDto con la información del usuario
        UserDto userDto = new UserDto(
            user.getId(),
            user.getEmail(),
            user.getRole()
        );

        // Retornar la respuesta con el formato esperado por el frontend
        return new LoginResponse(userDto, token);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        
        // Generar JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        
        // Crear el objeto UserDto
        UserDto userDto = new UserDto(
            user.getId(),
            user.getEmail(),
            user.getNombre(),
            user.getRole()
        );
        
        // Retornar la respuesta con el formato esperado por el frontend
        return ResponseEntity.ok(new LoginResponse(userDto, token));
    }
}