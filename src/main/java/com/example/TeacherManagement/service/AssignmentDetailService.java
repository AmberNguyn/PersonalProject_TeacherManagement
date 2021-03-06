package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHoursDto;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssignmentDetailService {
    List<AssignmentDetail> getAll();

    Optional<AssignmentDetail> getById(Integer id);

    AssignmentDetail create(AssignmentDetail assignmentDetail);
    AssignmentDetail create(AssignmentDetailRequest assignmentDetailRequest);

    Optional<AssignmentDetail> findById(Integer id);

    AssignmentDetail update(AssignmentDetailRequest assignmentDetailRequest, Integer id);

    void deleteById(Integer id);





    //find list of teacher assignment detail and classes that they have before a specific time
    List<AssignmentDetail> findAssignmentDetailListByStartDateAfterAndEmployeeCode(LocalDate startDate, String teacherCode);

    //find teacher assignment detail by code and the class they are in charge - for deleting and updating
    Optional<AssignmentDetail> findAssignmentDetailByTeacherCodeAndClassId(String teacherCode, String classId);

    void deleteAssignmentDetailByEmployeeCodeAndClassId(String teacherCode, String classId);


    List<TeacherLeaveNoteAndActiveHoursDto> findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();

    List<TeacherAndTheirNumberOfClassesDto> findTeacherAndTheirNumberOfClassInAMonth(Integer month);

    List<TeacherAndTotalActiveHoursDto> findTeachersAndTheirTotalActiveHoursInAMonth(Integer month);

    List<String> findTeacherListWhoHaveBeenPaidOrHaveNotBeenPaidInMonth(String isPaid, Integer month);

    Integer findIncomeBeforeTaxByAssignmentDetailIdUsingExpectedHours(Integer id);

    Integer findIncomeTaxByAssignmentDetailIdUsingExpectedHours(Integer id);

    Integer findTransferredAmountByAssignmentDetailIdUsingExpected(Integer id);

    Integer findIncomeBeforeTaxByAssignmentDetailIdUsingActiveHours(Integer id);

    Integer findTaxByAssignmentDetailIdUsingActiveHours(Integer id);

    Integer findTransferredAmountByAssignmentDetailIdUsingActiveHours(Integer id);


}
