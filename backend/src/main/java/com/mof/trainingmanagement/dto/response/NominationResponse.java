package com.mof.trainingmanagement.dto.response;

import com.mof.trainingmanagement.entity.NominationStatus;

import java.time.Instant;

public record NominationResponse(
        Long id,
        Long officerId,
        String officerName,
        Long trainingProgramId,
        String trainingProgramName,
        NominationStatus status,
        Instant nominatedAt
) {
}
