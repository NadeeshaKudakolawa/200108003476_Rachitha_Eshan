package com.mof.trainingmanagement.controller;

import com.mof.trainingmanagement.dto.request.EligibilityRuleRequest;
import com.mof.trainingmanagement.dto.response.EligibilityRuleResponse;
import com.mof.trainingmanagement.service.EligibilityRuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/training-programs/{trainingProgramId}/eligibility-rules")
public class EligibilityRuleController {

    private final EligibilityRuleService eligibilityRuleService;

    public EligibilityRuleController(EligibilityRuleService eligibilityRuleService) {
        this.eligibilityRuleService = eligibilityRuleService;
    }

    @PostMapping
    public ResponseEntity<EligibilityRuleResponse> addRule(
            @PathVariable Long trainingProgramId,
            @Valid @RequestBody EligibilityRuleRequest request) {
        EligibilityRuleResponse response = eligibilityRuleService.addRule(trainingProgramId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EligibilityRuleResponse>> getRules(@PathVariable Long trainingProgramId) {
        return ResponseEntity.ok(eligibilityRuleService.getRulesForProgram(trainingProgramId));
    }
}