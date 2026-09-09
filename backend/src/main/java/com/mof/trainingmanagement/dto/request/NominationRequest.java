package com.mof.trainingmanagement.dto.request;

import jakarta.validation.constraints.NotNull;

public class NominationRequest {

    @NotNull(message = "Officer ID is required")
    private Long officerId;

    @NotNull(message = "Training programme ID is required")
    private Long trainingProgramId;

    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public Long getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(Long trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }
}
