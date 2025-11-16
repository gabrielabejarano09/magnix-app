package com.example.magnix.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private Long enrollingPlayerId;

    private String teamName;

    @ElementCollection
    private List<Long> memberIds;

    public Enrollment() {}

    public Enrollment(Long tournamentId, Long enrollingPlayerId, String teamName, List<Long> memberIds) {
        this.tournamentId = tournamentId;
        this.enrollingPlayerId = enrollingPlayerId;
        this.teamName = teamName;
        this.memberIds = memberIds;
    }

    public Long getId() {
        return id;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public Long getEnrollingPlayerId() {
        return enrollingPlayerId;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Long> getMemberIds() {
        return memberIds;
    }
}
