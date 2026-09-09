package com.mof.trainingmanagement.mapper;

import com.mof.trainingmanagement.dto.response.TrainingProgramResponse;
import com.mof.trainingmanagement.entity.TrainingProgram;
import org.springframework.stereotype.Component;

@Component
public class TrainingProgramMapper {

    public TrainingProgramResponse toResponse(TrainingProgram program) {
        return new TrainingProgramResponse(
                program.getId(),
                program.getName(),
                program.getMaximumParticipants()
        );
    }
}
