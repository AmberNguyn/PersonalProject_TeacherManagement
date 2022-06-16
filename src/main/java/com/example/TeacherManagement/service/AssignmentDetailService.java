package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHours;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssignmentDetailService {
    List<AssignmentDetail> getAll();

    AssignmentDetail addAssignmentDetail(AssignmentDetail assignmentDetail);


    Optional<AssignmentDetail> findAssignmentDetailById(Integer id);

//    public void deleteAssignmentDetailByEmployeeCodeAndStartDate(String teacherCode, LocalDate startDate);


    //find list of teacher assignment detail and classes that they have before a specific time
    List<AssignmentDetail> findAssignmentDetailListByStartDateAfterAndEmployeeCode(LocalDate startDate, String teacherCode);

    //find teacher assignment detail by code and the class they are in charge - for deleting and updating
    Optional<AssignmentDetail> findAssignmentDetailByTeacherCodeAndClassId(String teacherCode, String classId);

    void deleteAssignmentDetailByEmployeeCodeAndClassId(String teacherCode, String classId);

    List<TeacherLeaveNoteAndActiveHoursDto> findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();

    List<TeacherAndTheirNumberOfClassesDto> findTeacherAndTheirNumberOfClassInAMonth(Integer month);

    List<TeacherAndTotalActiveHours> findTeachersAndTheirTotalActiveHoursInAMonth(Integer month);



}
