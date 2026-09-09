package com.mof.trainingmanagement.repository;

import com.mof.trainingmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}