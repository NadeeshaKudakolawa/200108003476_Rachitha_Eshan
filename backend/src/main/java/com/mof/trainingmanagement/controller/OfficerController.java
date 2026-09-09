package com.mof.trainingmanagement.controller;

import com.mof.trainingmanagement.dto.request.OfficerRequest;
import com.mof.trainingmanagement.dto.response.OfficerResponse;
import com.mof.trainingmanagement.service.OfficerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/officers")
@CrossOrigin(origins = "http://localhost:3000")
public class OfficerController {

    private final OfficerService officerService;

    public OfficerController(OfficerService officerService) {
        this.officerService = officerService;
    }

    @PostMapping
    public ResponseEntity<OfficerResponse> createOfficer(@Valid @RequestBody OfficerRequest request) {
        OfficerResponse response = officerService.createOfficer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OfficerResponse>> getAllOfficers() {
        return ResponseEntity.ok(officerService.getAllOfficers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficerResponse> getOfficerById(@PathVariable Long id) {
        return ResponseEntity.ok(officerService.getOfficerById(id));
    }
}