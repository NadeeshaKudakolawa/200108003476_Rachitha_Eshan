package com.mof.trainingmanagement.service.impl;

import com.mof.trainingmanagement.dto.request.TrainingProgramRequest;
import com.mof.trainingmanagement.dto.response.TrainingProgramResponse;
import com.mof.trainingmanagement.entity.TrainingProgram;
import com.mof.trainingmanagement.exception.ResourceNotFoundException;
import com.mof.trainingmanagement.mapper.TrainingProgramMapper;
import com.mof.trainingmanagement.repository.TrainingProgramRepository;
import com.mof.trainingmanagement.service.TrainingProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {

    private final TrainingProgramRepository trainingProgramRepository;
    private final TrainingProgramMapper trainingProgramMapper;

    public TrainingProgramServiceImpl(TrainingProgramRepository trainingProgramRepository,
                                      TrainingProgramMapper trainingProgramMapper) {
        this.trainingProgramRepository = trainingProgramRepository;
        this.trainingProgramMapper = trainingProgramMapper;
    }

    @Override
    public TrainingProgramResponse createTrainingProgram(TrainingProgramRequest request) {
        TrainingProgram program = new TrainingProgram(
                request.getName().trim(),
                request.getMaximumParticipants()
        );
        return trainingProgramMapper.toResponse(trainingProgramRepository.save(program));
    }

    @Override
    public List<TrainingProgramResponse> getAllTrainingPrograms() {
        return trainingProgramRepository.findAll()
                .stream()
                .map(trainingProgramMapper::toResponse)
                .toList();
    }

    @Override
    public TrainingProgramResponse getTrainingProgramById(Long id) {
        TrainingProgram program = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Training programme not found with id: " + id));
        return trainingProgramMapper.toResponse(program);
    }
}
