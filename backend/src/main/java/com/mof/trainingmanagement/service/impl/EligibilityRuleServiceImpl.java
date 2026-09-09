package com.mof.trainingmanagement.service.impl;

import com.mof.trainingmanagement.dto.request.EligibilityRuleRequest;
import com.mof.trainingmanagement.dto.response.EligibilityRuleResponse;
import com.mof.trainingmanagement.entity.EligibilityRule;
import com.mof.trainingmanagement.entity.TrainingProgram;
import com.mof.trainingmanagement.exception.ResourceNotFoundException;
import com.mof.trainingmanagement.mapper.EligibilityRuleMapper;
import com.mof.trainingmanagement.repository.EligibilityRuleRepository;
import com.mof.trainingmanagement.repository.TrainingProgramRepository;
import com.mof.trainingmanagement.service.EligibilityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityRuleServiceImpl implements EligibilityRuleService {

    private final EligibilityRuleRepository eligibilityRuleRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final EligibilityRuleMapper eligibilityRuleMapper;

    public EligibilityRuleServiceImpl(EligibilityRuleRepository eligibilityRuleRepository,
                                       TrainingProgramRepository trainingProgramRepository,
                                       EligibilityRuleMapper eligibilityRuleMapper) {
        this.eligibilityRuleRepository = eligibilityRuleRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.eligibilityRuleMapper = eligibilityRuleMapper;
    }

    @Override
    public EligibilityRuleResponse addRule(Long trainingProgramId, EligibilityRuleRequest request) {
        TrainingProgram program = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Training programme not found with id: " + trainingProgramId));

        EligibilityRule rule = new EligibilityRule(program, request.getRuleType(), request.getRuleValue());
        EligibilityRule saved = eligibilityRuleRepository.save(rule);
        return eligibilityRuleMapper.toResponse(saved);
    }

    @Override
    public List<EligibilityRuleResponse> getRulesForProgram(Long trainingProgramId) {
        return eligibilityRuleRepository.findByTrainingProgramId(trainingProgramId)
                .stream()
                .map(eligibilityRuleMapper::toResponse)
                .toList();
    }
}