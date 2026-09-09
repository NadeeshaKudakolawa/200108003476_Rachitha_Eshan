package com.mof.trainingmanagement.service.impl;

import com.mof.trainingmanagement.dto.request.DepartmentRequest;
import com.mof.trainingmanagement.dto.response.DepartmentResponse;
import com.mof.trainingmanagement.entity.Department;
import com.mof.trainingmanagement.exception.ResourceNotFoundException;
import com.mof.trainingmanagement.mapper.DepartmentMapper;
import com.mof.trainingmanagement.repository.DepartmentRepository;
import com.mof.trainingmanagement.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                  DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Department department = departmentMapper.toEntity(request);
        Department saved = departmentRepository.save(department);
        return departmentMapper.toResponse(saved);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return departmentMapper.toResponse(department);
    }
}