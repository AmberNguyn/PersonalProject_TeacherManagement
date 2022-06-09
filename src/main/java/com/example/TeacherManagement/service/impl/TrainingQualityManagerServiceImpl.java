package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.TrainingQualityManager;
import com.example.TeacherManagement.repository.TrainingQualityManagerRepository;
import com.example.TeacherManagement.service.TrainingQualityManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingQualityManagerServiceImpl implements TrainingQualityManagerService {
    @Autowired
    private final TrainingQualityManagerRepository trainingQualityManagerRepository;

    @Override
    public List<TrainingQualityManager> getAll() {
        return trainingQualityManagerRepository.findAll();
    }

    @Override
    public TrainingQualityManager addTrainingQualityManager(TrainingQualityManager trainingQualityManager) {
        return trainingQualityManagerRepository.save(trainingQualityManager);
    }

    @Override
    public Optional<TrainingQualityManager> findTrainingQualityManagerByEmployeeCode(String TQMCode) {
        return Optional.of(trainingQualityManagerRepository.findTrainingQualityManagerByEmployeeCode(TQMCode));
    }

    @Override
    public void deleteTrainingQualityManagerByEmployeeCode(String TQMCode) {
        trainingQualityManagerRepository.delete(trainingQualityManagerRepository.findTrainingQualityManagerByEmployeeCode(TQMCode));
    }
}
