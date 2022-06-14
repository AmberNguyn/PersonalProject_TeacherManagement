package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.AssignmentDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssignmentDetailService {
    List<AssignmentDetail> getAll();

    AssignmentDetail addAssignmentDetail(AssignmentDetail assignmentDetail);

    Optional<AssignmentDetail> findAssignmentDetailByStartDateAndEmployeeCode(String teacherCode, LocalDate startDate);

    Optional<AssignmentDetail> findAssignmentDetailById(Integer id);
    public void deleteAssignmentDetailByEmployeeCodeAndStartDate(String teacherCode, LocalDate startDate);
}
