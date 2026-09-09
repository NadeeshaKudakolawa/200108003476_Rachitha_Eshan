package com.mof.trainingmanagement.service;

import com.mof.trainingmanagement.dto.request.TrainingProgramRequest;
import com.mof.trainingmanagement.dto.response.TrainingProgramResponse;

import java.util.List;

public interface TrainingProgramService {

    TrainingProgramResponse createTrainingProgram(TrainingProgramRequest request);

    List<TrainingProgramResponse> getAllTrainingPrograms();

    TrainingProgramResponse getTrainingProgramById(Long id);
}
