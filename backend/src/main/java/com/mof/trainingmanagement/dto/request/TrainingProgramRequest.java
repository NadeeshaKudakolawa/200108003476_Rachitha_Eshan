package com.mof.trainingmanagement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TrainingProgramRequest {

    @NotBlank(message = "Training programme name is required")
    private String name;

    @Min(value = 1, message = "Maximum participants must be at least 1")
    private int maximumParticipants;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaximumParticipants() {
        return maximumParticipants;
    }

    public void setMaximumParticipants(int maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
    }
}
