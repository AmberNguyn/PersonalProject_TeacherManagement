package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHours;
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

    //find a list of teachers with their leave not and active hours
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
    @Query(value = "SELECT new com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHours(tc.employeeCode, sum(ad.activeHours)) " +
            "FROM AssignmentDetail ad JOIN Contract ct ON ad.contract.id = ct.id " +
            "JOIN Teacher tc ON ct.teacher.id = tc.id " +
            "WHERE (EXTRACT (MONTH FROM ad.courseStartDate) = ?1 ) " +
            "GROUP BY tc.employeeCode")
    List<TeacherAndTotalActiveHours> findTeachersAndTheirTotalActiveHoursInAMonth(Integer month);

    // list of teachers who are paid / haven;t been paid in a month ============
//    SELECT tc.employee_code, ad.clazz_id
//    FROM assignment_detail ad JOIN contract ct ON ad.contract_id = ct.id
//    JOIN teacher tc ON ct.teacher_id = tc.id
//    JOIN payment pm ON pm.assignment_detail_id = ad.id
//    WHERE pm.is_paid = 'false' AND (EXTRACT (MONTH FROM ad.course_start_date) = 6 )
//    GROUP BY tc.employee_code, ad.clazz_id








}