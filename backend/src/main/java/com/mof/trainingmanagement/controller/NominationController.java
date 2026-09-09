package com.mof.trainingmanagement.controller;

import com.mof.trainingmanagement.dto.request.NominationRequest;
import com.mof.trainingmanagement.dto.response.NominationResponse;
import com.mof.trainingmanagement.service.NominationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nominations")
@CrossOrigin(origins = "http://localhost:3000")
public class NominationController {

    private final NominationService nominationService;

    public NominationController(NominationService nominationService) {
        this.nominationService = nominationService;
    }

    @PostMapping
    public ResponseEntity<NominationResponse> createNomination(
            @Valid @RequestBody NominationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nominationService.createNomination(request));
    }

    @GetMapping("/program/{trainingProgramId}")
    public ResponseEntity<List<NominationResponse>> getNominationsForProgram(
            @PathVariable Long trainingProgramId) {
        return ResponseEntity.ok(nominationService.getNominationsForProgram(trainingProgramId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NominationResponse> cancelNomination(@PathVariable Long id) {
        return ResponseEntity.ok(nominationService.cancelNomination(id));
    }
}
