package com.mof.trainingmanagement.service;

import com.mof.trainingmanagement.dto.request.DepartmentRequest;
import com.mof.trainingmanagement.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest request);

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);
}