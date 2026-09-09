package com.mof.trainingmanagement.service;

import com.mof.trainingmanagement.dto.request.OfficerRequest;
import com.mof.trainingmanagement.dto.response.OfficerResponse;

import java.util.List;

public interface OfficerService {

    OfficerResponse createOfficer(OfficerRequest request);

    List<OfficerResponse> getAllOfficers();

    OfficerResponse getOfficerById(Long id);
}