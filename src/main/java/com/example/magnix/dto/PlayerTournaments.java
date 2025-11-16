package com.example.magnix.dto;

import com.example.magnix.model.Tournament;
import java.util.List;

public class PlayerTournaments {

    private List<Tournament> openTournaments;
    private List<Tournament> enrolledTournaments;

    public PlayerTournaments(List<Tournament> openTournaments, List<Tournament> enrolledTournaments) {
        this.openTournaments = openTournaments;
        this.enrolledTournaments = enrolledTournaments;
    }

    public List<Tournament> getOpenTournaments() {
        return openTournaments;
    }

    public List<Tournament> getEnrolledTournaments() {
        return enrolledTournaments;
    }
}
