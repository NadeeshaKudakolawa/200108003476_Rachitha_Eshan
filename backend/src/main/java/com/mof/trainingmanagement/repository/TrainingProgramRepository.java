package com.mof.trainingmanagement.repository;

import com.mof.trainingmanagement.entity.TrainingProgram;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TrainingProgram> findWithLockById(Long id);
}
