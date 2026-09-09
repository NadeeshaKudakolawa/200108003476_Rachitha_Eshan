package com.mof.trainingmanagement.dto.response;

import com.mof.trainingmanagement.entity.RuleType;

public class EligibilityRuleResponse {

    private Long id;
    private Long trainingProgramId;
    private RuleType ruleType;
    private String ruleValue;

    public EligibilityRuleResponse() {
    }

    public EligibilityRuleResponse(Long id, Long trainingProgramId, RuleType ruleType, String ruleValue) {
        this.id = id;
        this.trainingProgramId = trainingProgramId;
        this.ruleType = ruleType;
        this.ruleValue = ruleValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(Long trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }
}