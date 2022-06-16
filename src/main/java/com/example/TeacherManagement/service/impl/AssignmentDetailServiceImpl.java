package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.exception.InvalidMonth;
import com.example.TeacherManagement.repository.AssignmentDetailRepository;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHours;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignmentDetailServiceImpl implements AssignmentDetailService {
    @Autowired
    public final AssignmentDetailRepository assignmentDetailRepository;

    @Override
    public List<AssignmentDetail> getAll() {
        return assignmentDetailRepository.findAll();
    }

    @Override
    public AssignmentDetail addAssignmentDetail(AssignmentDetail assignmentDetail) {
        return assignmentDetailRepository.save(assignmentDetail);
    }

    @Override
    public List<AssignmentDetail> findAssignmentDetailListByStartDateAfterAndEmployeeCode(LocalDate startDate, String teacherCode) {
        return assignmentDetailRepository.findAssignmentDetailByCourseStartDateAfterAndContractTeacherEmployeeCodeContaining(startDate, teacherCode);
    }

    @Override
    public Optional<AssignmentDetail> findAssignmentDetailByTeacherCodeAndClassId(String teacherCode, String classId) {
        return Optional.of(assignmentDetailRepository.findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(teacherCode, classId));
    }

    @Override
    public Optional<AssignmentDetail> findAssignmentDetailById(Integer id) {
        return assignmentDetailRepository.findById(id);
    }

    @Override
    public void deleteAssignmentDetailByEmployeeCodeAndClassId(String teacherCode, String classId) {
            assignmentDetailRepository.delete(assignmentDetailRepository.findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(teacherCode, classId));
    }

    @Override
    public List<TeacherLeaveNoteAndActiveHoursDto> findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours() {
        return assignmentDetailRepository.findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();
    }

    @Override //check interger between 1-12
    public List<TeacherAndTheirNumberOfClassesDto> findTeacherAndTheirNumberOfClassInAMonth(Integer month) {
        return assignmentDetailRepository.findTeacherAndTheirNumberOfClass(month);
    }

    @Override
    public List<TeacherAndTotalActiveHours> findTeachersAndTheirTotalActiveHoursInAMonth(Integer month) {
        return assignmentDetailRepository.findTeachersAndTheirTotalActiveHoursInAMonth(month);
    }

}
