package com.mof.trainingmanagement.dto.request;

import com.mof.trainingmanagement.entity.RuleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EligibilityRuleRequest {

    @NotNull(message = "Rule type is required")
    private RuleType ruleType;

    @NotBlank(message = "Rule value is required")
    private String ruleValue;

    public EligibilityRuleRequest() {
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