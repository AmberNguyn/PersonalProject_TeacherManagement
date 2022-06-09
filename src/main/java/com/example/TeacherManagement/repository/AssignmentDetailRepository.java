package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.AssignmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AssignmentDetailRepository extends JpaRepository<AssignmentDetail, Integer> {
    AssignmentDetail findAssignmentDetailByTeacherEmployeeCodeAndStartDate(String teacherCode, LocalDate startDate);
}
