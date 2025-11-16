package com.example.magnix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    private Long id;

    private String name;

    public Player() {}

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
