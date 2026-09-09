package com.mof.trainingmanagement.mapper;

import com.mof.trainingmanagement.dto.request.DepartmentRequest;
import com.mof.trainingmanagement.dto.response.DepartmentResponse;
import com.mof.trainingmanagement.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequest request) {
        return new Department(request.getName());
    }

    public DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(department.getId(), department.getName());
    }
}