package com.example.magnix.dto;

import java.time.LocalDateTime;

public class ReservationRequest {
    private Long playerId;
    private Long spaceId;
    private LocalDateTime startTime;

    public Long getPlayerId() {
        return playerId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
