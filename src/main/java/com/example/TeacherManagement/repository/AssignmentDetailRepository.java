package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHoursDto;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssignmentDetailRepository extends JpaRepository<AssignmentDetail, Integer> {
    //find list of teacher assignment detail and classes that they have after a specific time
    List<AssignmentDetail> findAssignmentDetailByCourseStartDateAfterAndContractTeacherEmployeeCodeContaining(LocalDate startDate, String teacherCode);

    //find teacher assignment detail by code and the class they are in charge - for deleting and updating
    AssignmentDetail findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(String teacherCode, String classId);

    //find a list of teachers with their leave note and active hours
    @Query(value = "SELECT new com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto(tc.employeeCode, tc.firstName, tc.lastName, ad.leaveNote, ad.activeHours, ad.expectedHours) " +
            "FROM AssignmentDetail ad " +
            "JOIN Contract ct ON ad.contract.id = ct.id " +
            "JOIN Teacher tc ON tc.id = ct.teacher.id " +
            "WHERE ad.activeHours < ad.expectedHours")
    List<TeacherLeaveNoteAndActiveHoursDto> findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();


    //find a list of teachers and how many classes they have
    @Query(value = "SELECT new com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto(tc.employeeCode, count(ad.contract.id)) " +
            "FROM AssignmentDetail ad JOIN Contract ct ON ad.contract.id = ct.id " +
            "JOIN Teacher tc ON ct.teacher.id = tc.id " +
            "WHERE (EXTRACT (MONTH FROM ad.courseStartDate) = ?1 ) " +
            "GROUP BY tc.employeeCode")
    List<TeacherAndTheirNumberOfClassesDto> findTeacherAndTheirNumberOfClass(Integer month);

    //find a list of teachers and total of their active hours
    @Query(value = "SELECT new com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHoursDto(tc.employeeCode, sum(ad.activeHours)) " +
            "FROM AssignmentDetail ad JOIN Contract ct ON ad.contract.id = ct.id " +
            "JOIN Teacher tc ON ct.teacher.id = tc.id " +
            "WHERE (EXTRACT (MONTH FROM ad.courseStartDate) = ?1 ) " +
            "GROUP BY tc.employeeCode")
    List<TeacherAndTotalActiveHoursDto> findTeachersAndTheirTotalActiveHoursInAMonth(Integer month);

    // list of teachers who are paid / haven't been paid in a month
    @Query(value = "SELECT tc.employeeCode " +
            "FROM AssignmentDetail ad JOIN Contract ct ON ad.contract.id = ct.id " +
            "JOIN Teacher tc ON ct.teacher.id = tc.id " +
            "JOIN Payment pm ON pm.assignmentDetail.id = ad.id " +
            "WHERE pm.isPaid = ?1 AND (EXTRACT (MONTH FROM pm.transferredDate) = ?2 )" +
            "GROUP BY tc.employeeCode, ad.clazz.id")
    List<String> findTeacherListWhoHaveBeenPairOrHaveNotBeenPaidInMonth(Boolean isPaid, Integer month);

    //find income before tax by AssignmentDetailId (expected hours)
    @Query(value = "SELECT (ad.expectedHours * ad.payRate) FROM AssignmentDetail ad " +
            "WHERE ad.id = ?1")
    Integer findIncomeBeforeTaxByAssignmentDetailIdUsingExpectedHours(Integer id);

    //find tax  (expected hours)
    @Query(value = "SELECT (ad.expectedHours * ad.payRate * 0.1) FROM AssignmentDetail ad " +
            "WHERE ad.id = ?1")
    Integer findIncomeTaxByAssignmentDetailIdUsingExpectedHours(Integer id);

    //find transferred amount  (expected hours)
    @Query(value = "SELECT ((ad.expectedHours * ad.payRate) - (ad.expectedHours * ad.payRate * 0.1)) FROM AssignmentDetail ad " +
            "WHERE ad.id = ?1")
    Integer findTransferredAmountByAssignmentDetailIdUsingExpectedHours(Integer id);

    //find income before tax by AssignmentDetailId (active hours)
    @Query(value = "SELECT (ad.activeHours * ad.payRate) FROM AssignmentDetail ad " +
            "WHERE ad.id = ?1")
    Integer findIncomeBeforeTaxByAssignmentDetailIdUsingActiveHours(Integer id);
    //find tax (active hours)

    @Query(value = "SELECT (ad.activeHours * ad.payRate * 0.1) FROM AssignmentDetail ad " +
            "WHERE ad.id = ?1")
    Integer findTaxByAssignmentDetailIdUsingActiveHours(Integer id);
    //find transferred amount (active hours)

    @Query(value = "SELECT ((ad.activeHours * ad.payRate) - (ad.activeHours * ad.payRate * 0.1)) FROM AssignmentDetail ad " +
            "WHERE ad.id = ?1")
    Integer findTransferredAmountByAssignmentDetailIdUsingActiveHours(Integer id);

    //find teachers and their number of classes, total active hours





}