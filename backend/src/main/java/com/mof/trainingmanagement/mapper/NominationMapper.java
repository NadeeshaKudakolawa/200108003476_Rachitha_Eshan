package com.mof.trainingmanagement.mapper;

import com.mof.trainingmanagement.dto.response.NominationResponse;
import com.mof.trainingmanagement.entity.Nomination;
import org.springframework.stereotype.Component;

@Component
public class NominationMapper {

    public NominationResponse toResponse(Nomination nomination) {
        return new NominationResponse(
                nomination.getId(),
                nomination.getOfficer().getId(),
                nomination.getOfficer().getName(),
                nomination.getTrainingProgram().getId(),
                nomination.getTrainingProgram().getName(),
                nomination.getStatus(),
                nomination.getNominatedAt()
        );
    }
}
