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
    public Optional<TeacherObservation> findTeacherObservationByEmployeeCode(String teacherCode, LocalDate startDate, LocalDate endDate) {
        return Optional.of(teacherObservationRepository.findTeacherObservationByAssignmentDetailTeacherEmployeeCodeAndObservationDateBetween(teacherCode, startDate, endDate));
    }

    @Override
    public void deleteTeacherObservationByEmployeeCode(String teacherCode, LocalDate observationDate) {
        teacherObservationRepository.delete(teacherObservationRepository.findTeacherObservationByAssignmentDetailTeacherEmployeeCodeAndObservationDate(teacherCode, observationDate));
    }
}
