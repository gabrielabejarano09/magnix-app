package com.example.magnix.dto;

public class RegisterRequest {

    private String email;
    private String password;
    private String nombre;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String nombre) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}