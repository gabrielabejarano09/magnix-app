package com.example.magnix.dto;

public class LoginResponse {
    private String email;
    private String role;
    private String token; // ← AGREGAR CAMPO TOKEN

    public LoginResponse(String email, String role, String token) {
        this.email = email;
        this.role = role;
        this.token = token;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}