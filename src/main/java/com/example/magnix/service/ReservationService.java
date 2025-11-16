package com.example.magnix.service;

import com.example.magnix.exception.SlotNotAvailableException;
import com.example.magnix.model.Reservation;
import com.example.magnix.repository.ReservationRepository;
import com.example.magnix.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SpaceRepository spaceRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              SpaceRepository spaceRepository) {
        this.reservationRepository = reservationRepository;
        this.spaceRepository = spaceRepository;
    }

    public Reservation createReservation(Long playerId,
                                         Long spaceId,
                                         LocalDateTime startTime) {

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se puede reservar en el pasado");
        }

        LocalDateTime endTime = startTime.plusHours(1);

        List<Reservation> conflicts =
                reservationRepository.findConflictingReservations(spaceId, startTime, endTime);

        if (!conflicts.isEmpty()) {
            throw new SlotNotAvailableException("El horario no está disponible");
        }

        Reservation reservation = new Reservation(playerId, spaceId, startTime, endTime);
        return reservationRepository.save(reservation);
    }
}
