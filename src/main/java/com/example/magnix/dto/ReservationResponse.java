package com.example.magnix.dto;

import java.time.LocalDateTime;

public class ReservationResponse {
    private Long id;
    private Long playerId;
    private Long spaceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ReservationResponse(Long id, Long playerId, Long spaceId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.playerId = playerId;
        this.spaceId = spaceId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
