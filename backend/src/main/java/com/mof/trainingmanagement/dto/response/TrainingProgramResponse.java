package com.mof.trainingmanagement.dto.response;

public record TrainingProgramResponse(
        Long id,
        String name,
        int maximumParticipants
) {
}
