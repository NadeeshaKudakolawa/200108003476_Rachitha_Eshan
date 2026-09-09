package com.mof.trainingmanagement.mapper;

import com.mof.trainingmanagement.dto.response.OfficerResponse;
import com.mof.trainingmanagement.entity.Officer;
import org.springframework.stereotype.Component;

@Component
public class OfficerMapper {

    public OfficerResponse toResponse(Officer officer) {
        return new OfficerResponse(
                officer.getId(),
                officer.getEmployeeNumber(),
                officer.getName(),
                officer.getEmail(),
                officer.getDepartment().getId(),
                officer.getDepartment().getName(),
                officer.getGrade(),
                officer.getJoiningDate(),
                officer.getYearsOfService());
    }
}