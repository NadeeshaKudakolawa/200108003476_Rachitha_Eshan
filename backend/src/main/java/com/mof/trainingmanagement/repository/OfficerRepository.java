package com.mof.trainingmanagement.repository;

import com.mof.trainingmanagement.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer, Long> {

    boolean existsByEmployeeNumber(String employeeNumber);

    boolean existsByEmail(String email);
}