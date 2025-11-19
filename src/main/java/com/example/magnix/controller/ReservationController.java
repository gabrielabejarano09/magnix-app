package com.example.magnix.controller;

import com.example.magnix.dto.ReservationRequest;
import com.example.magnix.dto.ReservationResponse;
import com.example.magnix.model.Reservation;
import com.example.magnix.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ReservationResponse createReservation(@RequestBody ReservationRequest request) {
        Reservation reservation = service.createReservation(
                request.getPlayerId(),
                request.getSpaceId(),
                request.getStartTime()
        );

        return new ReservationResponse(
                reservation.getId(),
                reservation.getPlayerId(),
                reservation.getSpaceId(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }
}
