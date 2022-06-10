package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.TeacherObservation;
import com.example.TeacherManagement.repository.TeacherObservationRepository;
import com.example.TeacherManagement.service.TeacherObservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherObservationServiceImpl implements TeacherObservationService {
    @Autowired
    private final TeacherObservationRepository teacherObservationRepository;


    @Override
    public List<TeacherObservation> getAll() {
        return teacherObservationRepository.findAll();
    }

    @Override
    public TeacherObservation addTeacherObservation(TeacherObservation teacherObservation) {
        return teacherObservationRepository.save(teacherObservation);
    }

    @Override
    public Optional<TeacherObservation> findTeacherObservationByEmployeeCodeAndObservationDate(String teacherCode, LocalDate observationDate) {
        return Optional.of(teacherObservationRepository.findTeacherObservationByAssignmentDetailTeacherEmployeeCodeAndObservationDate(teacherCode, observationDate));
    }

    @Override
    public void deleteTeacherObservationByEmployeeCodeAndObservationDate(String teacherCode, LocalDate observationDate) {
        teacherObservationRepository.delete(teacherObservationRepository.findTeacherObservationByAssignmentDetailTeacherEmployeeCodeAndObservationDate(teacherCode, observationDate));
    }
}
