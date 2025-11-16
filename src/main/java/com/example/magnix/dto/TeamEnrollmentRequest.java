package com.example.magnix.dto;

import java.util.List;

public class TeamEnrollmentRequest {

    private String teamName;
    private List<Long> memberIds;

    public TeamEnrollmentRequest() {}

    public TeamEnrollmentRequest(String teamName, List<Long> memberIds) {
        this.teamName = teamName;
        this.memberIds = memberIds;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Long> getMemberIds() {
        return memberIds;
    }
}
