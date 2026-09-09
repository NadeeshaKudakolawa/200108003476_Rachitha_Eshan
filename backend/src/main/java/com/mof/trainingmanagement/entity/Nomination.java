package com.mof.trainingmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "nominations")
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "officer_id", nullable = false)
    private Officer officer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "training_program_id", nullable = false)
    private TrainingProgram trainingProgram;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NominationStatus status;

    @Column(name = "nominated_at", nullable = false)
    private Instant nominatedAt;

    public Nomination() {
    }

    public Nomination(Officer officer, TrainingProgram trainingProgram,
                      NominationStatus status, Instant nominatedAt) {
        this.officer = officer;
        this.trainingProgram = trainingProgram;
        this.status = status;
        this.nominatedAt = nominatedAt;
    }

    public Long getId() {
        return id;
    }

    public Officer getOfficer() {
        return officer;
    }

    public TrainingProgram getTrainingProgram() {
        return trainingProgram;
    }

    public NominationStatus getStatus() {
        return status;
    }

    public void setStatus(NominationStatus status) {
        this.status = status;
    }

    public Instant getNominatedAt() {
        return nominatedAt;
    }
}
