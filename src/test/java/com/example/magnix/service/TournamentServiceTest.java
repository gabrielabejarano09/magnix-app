package com.example.magnix.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.example.magnix.dto.PlayerTournaments;
import com.example.magnix.dto.TeamEnrollmentRequest;
import com.example.magnix.exception.PlayerNotFoundException;
import com.example.magnix.model.Enrollment;
import com.example.magnix.model.Player;
import com.example.magnix.model.Tournament;
import com.example.magnix.repository.EnrollmentRepository;
import com.example.magnix.repository.PlayerRepository;
import com.example.magnix.repository.TournamentRepository;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @Test
    @DisplayName("Debería devolver listas separadas de torneos abiertos e inscritos")
    void getTournamentsForPlayer_shouldReturnOpenAndEnrolledTournaments() {
        // Arrange
        Player player = new Player(1L, "jugador1");
        Tournament enrolledTournament = new Tournament(1L, "Torneo de Verano 2025");
        Tournament openTournament1 = new Tournament(2L, "Torneo de Otoño 2025");
        Tournament openTournament2 = new Tournament(3L, "Torneo Relámpago");
        
       
        when(tournamentRepository.findEnrolledByPlayerId(player.getId())).thenReturn(List.of(enrolledTournament));
       
        when(tournamentRepository.findOpenTournaments()).thenReturn(List.of(openTournament1, openTournament2, enrolledTournament));

        // Act
        PlayerTournaments result = tournamentService.getTournamentsForPlayer(player.getId());

        // Assert
        assertNotNull(result);
        assertTrue(result.getEnrolledTournaments().contains(enrolledTournament));
        assertEquals(1, result.getEnrolledTournaments().size());
        assertTrue(result.getOpenTournaments().contains(openTournament1));
        assertTrue(result.getOpenTournaments().contains(openTournament2));
        assertEquals(2, result.getOpenTournaments().size()); 
    }

   @Test
@DisplayName("Debería inscribir un equipo si todos los jugadores son válidos")
void enrollTeamInTournament_shouldSucceed_withValidPlayers() {

    long tournamentId = 1L;
    long enrollingPlayerId = 101L;
    List<Long> memberIds = List.of(102L, 103L);
    TeamEnrollmentRequest request = new TeamEnrollmentRequest("Los Invencibles", memberIds);

    when(tournamentRepository.findById(tournamentId))
            .thenReturn(Optional.of(new Tournament(tournamentId, "Torneo Voley")));

    when(playerRepository.existsById(anyLong()))
        .thenReturn(true);

    Enrollment saved = mock(Enrollment.class);
    when(saved.getTeamName()).thenReturn("Los Invencibles");
    when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(saved);

    Enrollment result = tournamentService.enrollTeam(tournamentId, enrollingPlayerId, request);

    assertNotNull(result);
    assertEquals("Los Invencibles", result.getTeamName());
    verify(enrollmentRepository).save(any(Enrollment.class));
}

    @Test
    @DisplayName("Debería lanzar una excepción al inscribir un equipo con un jugador inválido")
    void enrollTeamInTournament_shouldThrowException_withInvalidPlayerId() {
        // Arrange
        long tournamentId = 1L;
        long enrollingPlayerId = 101L;
        List<Long> memberIds = List.of(102L, 999L); 
        TeamEnrollmentRequest request = new TeamEnrollmentRequest("Equipo Fantasma", memberIds);

        when(tournamentRepository.findById(tournamentId)).thenReturn(Optional.of(new Tournament(tournamentId, "Torneo Voley")));
        when(playerRepository.existsById(101L)).thenReturn(true);
        when(playerRepository.existsById(102L)).thenReturn(true);
        when(playerRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        assertThrows(PlayerNotFoundException.class, () -> {
            tournamentService.enrollTeam(tournamentId, enrollingPlayerId, request);
        });

        verify(enrollmentRepository, never()).save(any(Enrollment.class));
    }
}