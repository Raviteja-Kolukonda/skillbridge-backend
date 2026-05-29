package com.skillbridge.dto;

import java.util.List;

public class SkillMatchResponse {

    private int matchScore;
    private List<String> missingSkills;

    public SkillMatchResponse() {
    }

    public SkillMatchResponse(int matchScore, List<String> missingSkills) {
        this.matchScore = matchScore;
        this.missingSkills = missingSkills;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}