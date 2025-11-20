package com.example.magnix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String role;

    // Constructor vacío requerido por JPA
    public User() {
    }

    // Constructor completo
    public User(String email, String password, String nombre, String role) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.role = role;
    }
    
    // Constructor sin nombre (para compatibilidad con código existente)
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.nombre = email.split("@")[0]; // Usar parte del email como nombre por defecto
        this.role = role;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}