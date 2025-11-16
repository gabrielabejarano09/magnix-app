package com.example.magnix.repository;

import com.example.magnix.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
            SELECT r FROM Reservation r 
            WHERE r.spaceId = :spaceId
              AND r.startTime < :endTime
              AND r.endTime > :startTime
            """)
    List<Reservation> findConflictingReservations(Long spaceId,
                                                  LocalDateTime startTime,
                                                  LocalDateTime endTime);
}
