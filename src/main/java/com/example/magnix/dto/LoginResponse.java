package com.example.magnix.dto;

public class LoginResponse {
    private UserDto user;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(UserDto user, String token) {
        this.user = user;
        this.token = token;
    }

    // Getters y Setters
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Clase interna para el objeto user
    public static class UserDto {
        private String id;
        private String email;
        private String nombre;
        private String rol;

        public UserDto() {
        }

        public UserDto(Long id, String email, String nombre, String rol) {
            this.id = String.valueOf(id);
            this.email = email;
            this.nombre = nombre;
            this.rol = rol;
        }

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }
    }
}