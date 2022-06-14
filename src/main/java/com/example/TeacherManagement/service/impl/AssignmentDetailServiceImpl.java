package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.repository.AssignmentDetailRepository;
import com.example.TeacherManagement.service.AssignmentDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<AssignmentDetail> findAssignmentDetailByStartDateAndEmployeeCode(String teacherCode, LocalDate startDate) {
        return Optional.of(assignmentDetailRepository.findAssignmentDetailByStartDateAndContractTeacherEmployeeCodeContaining(teacherCode, startDate));
    }

    @Override
    public void deleteAssignmentDetailByEmployeeCodeAndStartDate(String teacherCode, LocalDate startDate) {
            assignmentDetailRepository.delete(assignmentDetailRepository.findAssignmentDetailByStartDateAndContractTeacherEmployeeCodeContaining(teacherCode, startDate));
    }
}
