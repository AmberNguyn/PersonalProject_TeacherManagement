package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.TrainingQualityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingQualityManagerRepository extends JpaRepository<TrainingQualityManager, Integer> {
    TrainingQualityManager findTrainingQualityManagerByEmployeeCode(String TQMCode);
}
