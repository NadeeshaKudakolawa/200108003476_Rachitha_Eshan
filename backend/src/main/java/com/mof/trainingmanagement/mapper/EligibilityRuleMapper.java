package com.mof.trainingmanagement.mapper;

import com.mof.trainingmanagement.dto.response.EligibilityRuleResponse;
import com.mof.trainingmanagement.entity.EligibilityRule;
import org.springframework.stereotype.Component;

@Component
public class EligibilityRuleMapper {

    public EligibilityRuleResponse toResponse(EligibilityRule rule) {
        return new EligibilityRuleResponse(
                rule.getId(),
                rule.getTrainingProgram().getId(),
                rule.getRuleType(),
                rule.getRuleValue()
        );
    }
}