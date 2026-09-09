package com.mof.trainingmanagement.repository;

import com.mof.trainingmanagement.entity.EligibilityRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EligibilityRuleRepository extends JpaRepository<EligibilityRule, Long> {

    List<EligibilityRule> findByTrainingProgramId(Long trainingProgramId);
}