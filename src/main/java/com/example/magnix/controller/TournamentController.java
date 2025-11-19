package com.example.magnix.controller;

import com.example.magnix.dto.PlayerTournaments;
import com.example.magnix.dto.TeamEnrollmentRequest;
import com.example.magnix.model.Enrollment;
import com.example.magnix.service.TournamentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @GetMapping("/{playerId}")
    public PlayerTournaments getTournaments(@PathVariable Long playerId) {
        return service.getTournamentsForPlayer(playerId);
    }

    @PostMapping("/{tournamentId}/enroll/{playerId}")
    public Enrollment enrollTeam(@PathVariable Long tournamentId,
                                 @PathVariable Long playerId,
                                 @RequestBody TeamEnrollmentRequest request) {
        return service.enrollTeam(tournamentId, playerId, request);
    }
}
