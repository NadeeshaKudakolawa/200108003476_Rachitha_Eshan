package com.mof.trainingmanagement.service;

import com.mof.trainingmanagement.dto.request.EligibilityRuleRequest;
import com.mof.trainingmanagement.dto.response.EligibilityRuleResponse;

import java.util.List;

public interface EligibilityRuleService {

    EligibilityRuleResponse addRule(Long trainingProgramId, EligibilityRuleRequest request);

    List<EligibilityRuleResponse> getRulesForProgram(Long trainingProgramId);
}