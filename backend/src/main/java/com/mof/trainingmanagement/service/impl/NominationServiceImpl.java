package com.mof.trainingmanagement.service.impl;

import com.mof.trainingmanagement.dto.request.NominationRequest;
import com.mof.trainingmanagement.dto.response.NominationResponse;
import com.mof.trainingmanagement.entity.Nomination;
import com.mof.trainingmanagement.entity.NominationStatus;
import com.mof.trainingmanagement.entity.Officer;
import com.mof.trainingmanagement.entity.TrainingProgram;
import com.mof.trainingmanagement.exception.DuplicateNominationException;
import com.mof.trainingmanagement.exception.ResourceNotFoundException;
import com.mof.trainingmanagement.mapper.NominationMapper;
import com.mof.trainingmanagement.repository.NominationRepository;
import com.mof.trainingmanagement.repository.OfficerRepository;
import com.mof.trainingmanagement.repository.TrainingProgramRepository;
import com.mof.trainingmanagement.service.NominationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NominationServiceImpl implements NominationService {

    private final NominationRepository nominationRepository;
    private final OfficerRepository officerRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final NominationMapper nominationMapper;

    public NominationServiceImpl(NominationRepository nominationRepository,
                                 OfficerRepository officerRepository,
                                 TrainingProgramRepository trainingProgramRepository,
                                 NominationMapper nominationMapper) {
        this.nominationRepository = nominationRepository;
        this.officerRepository = officerRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.nominationMapper = nominationMapper;
    }

    @Override
    @Transactional
    public NominationResponse createNomination(NominationRequest request) {
        Officer officer = officerRepository.findById(request.getOfficerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Officer not found with id: " + request.getOfficerId()));
        TrainingProgram program = trainingProgramRepository.findWithLockById(request.getTrainingProgramId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Training programme not found with id: " + request.getTrainingProgramId()));

        if (nominationRepository.existsByOfficerIdAndTrainingProgramIdAndStatusNot(
                officer.getId(), program.getId(), NominationStatus.CANCELLED)) {
            throw new DuplicateNominationException(
                    "Officer is already nominated for this training programme.");
        }

        long confirmed = nominationRepository.countByTrainingProgramIdAndStatus(
                program.getId(), NominationStatus.CONFIRMED);
        NominationStatus status = confirmed < program.getMaximumParticipants()
                ? NominationStatus.CONFIRMED
                : NominationStatus.WAITLISTED;

        Nomination nomination = new Nomination(officer, program, status, Instant.now());
        return nominationMapper.toResponse(nominationRepository.save(nomination));
    }

    @Override
    @Transactional
    public List<NominationResponse> getNominationsForProgram(Long trainingProgramId) {
        if (!trainingProgramRepository.existsById(trainingProgramId)) {
            throw new ResourceNotFoundException(
                    "Training programme not found with id: " + trainingProgramId);
        }
        return nominationRepository.findByTrainingProgramIdOrderByNominatedAtAscIdAsc(trainingProgramId)
                .stream()
                .map(nominationMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public NominationResponse cancelNomination(Long id) {
        Nomination nomination = nominationRepository.findByIdAndStatus(id, NominationStatus.CONFIRMED)
                .orElseGet(() -> nominationRepository.findByIdAndStatus(id, NominationStatus.WAITLISTED)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Active nomination not found with id: " + id)));

        trainingProgramRepository.findWithLockById(nomination.getTrainingProgram().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Training programme not found with id: " + nomination.getTrainingProgram().getId()));

        boolean wasConfirmed = nomination.getStatus() == NominationStatus.CONFIRMED;
        nomination.setStatus(NominationStatus.CANCELLED);
        Nomination cancelled = nominationRepository.save(nomination);

        if (wasConfirmed) {
            nominationRepository
                    .findByTrainingProgramIdAndStatusOrderByNominatedAtAscIdAsc(
                            nomination.getTrainingProgram().getId(), NominationStatus.WAITLISTED)
                    .stream()
                    .findFirst()
                    .ifPresent(next -> {
                        next.setStatus(NominationStatus.CONFIRMED);
                        nominationRepository.save(next);
                    });
        }

        return nominationMapper.toResponse(cancelled);
    }
}
