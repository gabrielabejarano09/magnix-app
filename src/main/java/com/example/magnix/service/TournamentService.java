package com.example.magnix.service;

import com.example.magnix.dto.PlayerTournaments;
import com.example.magnix.dto.TeamEnrollmentRequest;
import com.example.magnix.exception.PlayerNotFoundException;
import com.example.magnix.model.Enrollment;
import com.example.magnix.model.Tournament;
import com.example.magnix.repository.EnrollmentRepository;
import com.example.magnix.repository.PlayerRepository;
import com.example.magnix.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final PlayerRepository playerRepository;
    private final EnrollmentRepository enrollmentRepository;

    public TournamentService(TournamentRepository tournamentRepository,
                             PlayerRepository playerRepository,
                             EnrollmentRepository enrollmentRepository) {
        this.tournamentRepository = tournamentRepository;
        this.playerRepository = playerRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public PlayerTournaments getTournamentsForPlayer(Long playerId) {

        List<Tournament> enrolled = tournamentRepository.findEnrolledByPlayerId(playerId);

        List<Tournament> open = tournamentRepository.findOpenTournaments()
                .stream()
                .filter(t -> !enrolled.contains(t))
                .toList();

        return new PlayerTournaments(open, enrolled);
    }

    public Enrollment enrollTeam(Long tournamentId,
                                 Long enrollingPlayerId,
                                 TeamEnrollmentRequest request) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found"));

        if (!playerRepository.existsById(enrollingPlayerId)) {
            throw new PlayerNotFoundException("Player not found: " + enrollingPlayerId);
        }

        for (Long memberId : request.getMemberIds()) {
            if (!playerRepository.existsById(memberId)) {
                throw new PlayerNotFoundException("Player not found: " + memberId);
            }
        }

        Enrollment enrollment = new Enrollment(
                tournament.getId(),
                enrollingPlayerId,
                request.getTeamName(),
                request.getMemberIds()
        );

        return enrollmentRepository.save(enrollment);
    }
}
