package com.example.magnix.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.magnix.exception.SlotNotAvailableException;
import com.example.magnix.model.Reservation;
import com.example.magnix.repository.ReservationRepository;
import com.example.magnix.repository.SpaceRepository;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private SpaceRepository spaceRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("Debería crear una reserva si el horario está disponible")
    void createReservation_shouldSucceed_whenSlotIsAvailable() {
        // Arrange
        long playerId = 1L;
        long spaceId = 10L; 
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(15).withMinute(0);

        
        when(reservationRepository.findConflictingReservations(spaceId, startTime, startTime.plusHours(1)))
            .thenReturn(Collections.emptyList());

        // Act
        Reservation newReservation = reservationService.createReservation(playerId, spaceId, startTime);

        // Assert
        assertNotNull(newReservation);
        assertEquals(playerId, newReservation.getPlayerId());
        verify(reservationRepository).save(any(Reservation.class));
    }
    
    @Test
    @DisplayName("Debería lanzar una excepción si el horario ya está ocupado")
    void createReservation_shouldThrowException_whenSlotIsOccupied() {
        // Arrange
        long playerId = 1L;
        long spaceId = 10L;
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
        
        
        when(reservationRepository.findConflictingReservations(spaceId, startTime, startTime.plusHours(1)))
            .thenReturn(List.of(new Reservation())); 

        // Act & Assert
        assertThrows(SlotNotAvailableException.class, () -> {
            reservationService.createReservation(playerId, spaceId, startTime);
        });
        
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    @DisplayName("Debería lanzar una excepción al intentar reservar en el pasado")
    void createReservation_shouldThrowException_forPastDate() {
        // Arrange
        long playerId = 1L;
        long spaceId = 10L;
        LocalDateTime pastTime = LocalDateTime.now().minusDays(1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            reservationService.createReservation(playerId, spaceId, pastTime);
        });
    }
}