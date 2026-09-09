package com.mof.trainingmanagement;

import com.mof.trainingmanagement.dto.request.NominationRequest;
import com.mof.trainingmanagement.dto.response.NominationResponse;
import com.mof.trainingmanagement.entity.Nomination;
import com.mof.trainingmanagement.entity.NominationStatus;
import com.mof.trainingmanagement.entity.Officer;
import com.mof.trainingmanagement.entity.TrainingProgram;
import com.mof.trainingmanagement.mapper.NominationMapper;
import com.mof.trainingmanagement.repository.NominationRepository;
import com.mof.trainingmanagement.repository.OfficerRepository;
import com.mof.trainingmanagement.repository.TrainingProgramRepository;
import com.mof.trainingmanagement.service.impl.NominationServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NominationServiceImplTest {

    private final NominationRepository nominationRepository = org.mockito.Mockito.mock(NominationRepository.class);
    private final OfficerRepository officerRepository = org.mockito.Mockito.mock(OfficerRepository.class);
    private final TrainingProgramRepository trainingProgramRepository =
            org.mockito.Mockito.mock(TrainingProgramRepository.class);
    private final NominationServiceImpl service = new NominationServiceImpl(
            nominationRepository,
            officerRepository,
            trainingProgramRepository,
            new NominationMapper()
    );

    @Test
    void confirmsNominationsUntilCapacityThenWaitlists() {
        TrainingProgram program = program(10L, 1);
        Officer first = officer(1L);
        Officer second = officer(2L);
        when(trainingProgramRepository.findWithLockById(10L)).thenReturn(Optional.of(program));
        when(officerRepository.findById(1L)).thenReturn(Optional.of(first));
        when(officerRepository.findById(2L)).thenReturn(Optional.of(second));
        when(nominationRepository.existsByOfficerIdAndTrainingProgramIdAndStatusNot(any(), any(), any()))
                .thenReturn(false);
        AtomicLong confirmedCount = new AtomicLong();
        when(nominationRepository.countByTrainingProgramIdAndStatus(10L, NominationStatus.CONFIRMED))
                .thenAnswer(invocation -> confirmedCount.getAndIncrement());
        when(nominationRepository.save(any(Nomination.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        assertThat(service.createNomination(request(1L, 10L)).status())
                .isEqualTo(NominationStatus.CONFIRMED);
        assertThat(service.createNomination(request(2L, 10L)).status())
                .isEqualTo(NominationStatus.WAITLISTED);
    }

    @Test
    void cancellingConfirmedNominationPromotesOldestWaitlistedNomination() {
        TrainingProgram program = program(10L, 1);
        Officer first = officer(1L);
        Officer second = officer(2L);
        Nomination confirmed = new Nomination(first, program, NominationStatus.CONFIRMED, Instant.now());
        Nomination waiting = new Nomination(second, program, NominationStatus.WAITLISTED,
                Instant.now().plusSeconds(1));
        when(nominationRepository.findByIdAndStatus(5L, NominationStatus.CONFIRMED))
                .thenReturn(Optional.of(confirmed));
        when(trainingProgramRepository.findWithLockById(10L)).thenReturn(Optional.of(program));
        when(nominationRepository.findByTrainingProgramIdAndStatusOrderByNominatedAtAscIdAsc(
                10L, NominationStatus.WAITLISTED)).thenReturn(java.util.List.of(waiting));
        when(nominationRepository.save(any(Nomination.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        NominationResponse response = service.cancelNomination(5L);

        assertThat(response.status()).isEqualTo(NominationStatus.CANCELLED);
        assertThat(waiting.getStatus()).isEqualTo(NominationStatus.CONFIRMED);
    }

    private static TrainingProgram program(Long id, int capacity) {
        TrainingProgram program = new TrainingProgram("Leadership", capacity);
        program.setId(id);
        return program;
    }

    private static Officer officer(Long id) {
        Officer officer = new Officer();
        officer.setId(id);
        officer.setName("Officer " + id);
        return officer;
    }

    private static NominationRequest request(Long officerId, Long programId) {
        NominationRequest request = new NominationRequest();
        request.setOfficerId(officerId);
        request.setTrainingProgramId(programId);
        return request;
    }
}
