package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentDetailRepository extends JpaRepository<AssignmentDetail, Integer> {
    //find list of teacher assignment detail and classes that they have after a specific time
    List<AssignmentDetail> findAssignmentDetailByCourseStartDateAfterAndContractTeacherEmployeeCodeContaining(LocalDate startDate, String teacherCode);

    //find teacher assignment detail by code and the class they are in charge - for deleting and updating
    AssignmentDetail findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(String teacherCode, String classId);

    //find a list of teachers with their leave not and active hours

    List<TeacherLeaveNoteAndActiveHoursDto> findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();
