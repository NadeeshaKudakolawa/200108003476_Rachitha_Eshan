package com.mof.trainingmanagement.service.impl;

import com.mof.trainingmanagement.dto.request.OfficerRequest;
import com.mof.trainingmanagement.dto.response.OfficerResponse;
import com.mof.trainingmanagement.entity.Department;
import com.mof.trainingmanagement.entity.Officer;
import com.mof.trainingmanagement.exception.DuplicateOfficerException;
import com.mof.trainingmanagement.exception.ResourceNotFoundException;
import com.mof.trainingmanagement.mapper.OfficerMapper;
import com.mof.trainingmanagement.repository.DepartmentRepository;
import com.mof.trainingmanagement.repository.OfficerRepository;
import com.mof.trainingmanagement.service.OfficerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerServiceImpl implements OfficerService {

    private final OfficerRepository officerRepository;
    private final DepartmentRepository departmentRepository;
    private final OfficerMapper officerMapper;

    public OfficerServiceImpl(OfficerRepository officerRepository,
                               DepartmentRepository departmentRepository,
                               OfficerMapper officerMapper) {
        this.officerRepository = officerRepository;
        this.departmentRepository = departmentRepository;
        this.officerMapper = officerMapper;
    }

    @Override
    public OfficerResponse createOfficer(OfficerRequest request) {
        if (officerRepository.existsByEmployeeNumber(request.getEmployeeNumber())) {
            throw new DuplicateOfficerException(
                    "An officer with employee number '" + request.getEmployeeNumber() + "' already exists.");
        }
        if (officerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateOfficerException(
                    "An officer with email '" + request.getEmail() + "' already exists.");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + request.getDepartmentId()));

        Officer officer = new Officer(
                request.getEmployeeNumber(),
                request.getName(),
                request.getEmail(),
                department
        );

        Officer saved = officerRepository.save(officer);
        return officerMapper.toResponse(saved);
    }

    @Override
    public List<OfficerResponse> getAllOfficers() {
        return officerRepository.findAll()
                .stream()
                .map(officerMapper::toResponse)
                .toList();
    }

    @Override
    public OfficerResponse getOfficerById(Long id) {
        Officer officer = officerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Officer not found with id: " + id));
        return officerMapper.toResponse(officer);
    }
}