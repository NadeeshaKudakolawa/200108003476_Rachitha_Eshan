package com.mof.trainingmanagement.service;

import com.mof.trainingmanagement.dto.request.NominationRequest;
import com.mof.trainingmanagement.dto.response.NominationResponse;

import java.util.List;

public interface NominationService {

    NominationResponse createNomination(NominationRequest request);

    List<NominationResponse> getNominationsForProgram(Long trainingProgramId);

    NominationResponse cancelNomination(Long id);
}
