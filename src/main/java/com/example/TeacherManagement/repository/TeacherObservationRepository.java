package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.TeacherObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TeacherObservationRepository extends JpaRepository<TeacherObservation, Integer> {
    TeacherObservation findTeacherObservationByAssignmentDetailTeacherEmployeeCodeAndObservationDateBetween(String teacherCode, LocalDate startDate, LocalDate endDate);

    TeacherObservation findTeacherObservationByAssignmentDetailTeacherEmployeeCodeAndObservationDate(String teacherCode, LocalDate observationDate);
}
