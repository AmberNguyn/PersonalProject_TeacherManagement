package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.TrainingQualityManager;

import java.util.List;
import java.util.Optional;

public interface TrainingQualityManagerService {
    List<TrainingQualityManager> getAll();

    TrainingQualityManager addTrainingQualityManager(TrainingQualityManager trainingQualityManager);

    Optional<TrainingQualityManager> findTrainingQualityManagerByEmployeeCode(String TQMCode);

    void deleteTrainingQualityManagerByEmployeeCode(String TQMCode);
}
