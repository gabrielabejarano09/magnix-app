package com.example.magnix.repository;

import com.example.magnix.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query("""
            SELECT t FROM Tournament t
            WHERE t.id IN (
                SELECT e.tournamentId FROM Enrollment e WHERE e.enrollingPlayerId = :playerId
            )
            """)
    List<Tournament> findEnrolledByPlayerId(Long playerId);

    @Query("SELECT t FROM Tournament t")
    List<Tournament> findOpenTournaments();
}
