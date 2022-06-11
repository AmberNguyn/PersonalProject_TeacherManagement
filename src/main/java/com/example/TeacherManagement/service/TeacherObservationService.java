package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.TeacherObservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherObservationService {
    List<TeacherObservation> getAll();

    TeacherObservation addTeacherObservation(TeacherObservation teacherObservation);

    Optional<TeacherObservation> findTeacherObservationByEmployeeCodeAndObservationDate(String teacherCode, LocalDate observationDate);

    void deleteTeacherObservationByEmployeeCodeAndObservationDate(String teacherCode, LocalDate observationDate);
}
