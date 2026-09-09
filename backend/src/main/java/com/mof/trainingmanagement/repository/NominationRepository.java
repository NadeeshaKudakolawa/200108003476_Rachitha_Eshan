package com.mof.trainingmanagement.repository;

import com.mof.trainingmanagement.entity.Nomination;
import com.mof.trainingmanagement.entity.NominationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NominationRepository extends JpaRepository<Nomination, Long> {

    boolean existsByOfficerIdAndTrainingProgramIdAndStatusNot(
            Long officerId, Long trainingProgramId, NominationStatus status);

    long countByTrainingProgramIdAndStatus(Long trainingProgramId, NominationStatus status);

    List<Nomination> findByTrainingProgramIdAndStatusOrderByNominatedAtAscIdAsc(
            Long trainingProgramId, NominationStatus status);

    Optional<Nomination> findByIdAndStatus(Long id, NominationStatus status);

    List<Nomination> findByTrainingProgramIdOrderByNominatedAtAscIdAsc(Long trainingProgramId);
}
