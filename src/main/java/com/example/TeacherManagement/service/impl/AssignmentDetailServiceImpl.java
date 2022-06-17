package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.exception.InvalidMonth;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.repository.AssignmentDetailRepository;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.ContractService;
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
    @Autowired
    public final ClazzService clazzService;
    @Autowired
    public final ContractService contractService;
    @Autowired
    public final AssignmentDetailService assignmentDetailService;


    @Override
    public List<AssignmentDetail> getAll() {
        return assignmentDetailRepository.findAll();
    }

    @Override
    public Optional<AssignmentDetail> getById(Integer id) {
        return assignmentDetailRepository.findById(id);
    }

    @Override
    public AssignmentDetail create(AssignmentDetail assignmentDetail) {
        return assignmentDetailRepository.save(assignmentDetail);
    }

    @Override
    public AssignmentDetail create(AssignmentDetailRequest assignmentDetailRequest) throws ResourceNotFoundException {
        Clazz requestClazz = clazzService.findClassByClassId(assignmentDetailRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getClassId() + " not found"));

        Contract requestContract = contractService.findContractByContractId(assignmentDetailRequest.getContractId())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getContractId() + " not found!"));

        return assignmentDetailService.create(
                new AssignmentDetail(
                        null,
                        assignmentDetailRequest.getCourseStartDate(),
                        assignmentDetailRequest.getCourseStartDate(),
                        assignmentDetailRequest.getExpectedHours(),
                        assignmentDetailRequest.getActiveHours(),
                        assignmentDetailRequest.getLeaveNote(),
                        assignmentDetailRequest.getPayRate(),
                        requestContract,
                        requestClazz
                )
        );
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
    public Optional<AssignmentDetail> findById(Integer id) {
        return assignmentDetailRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        assignmentDetailRepository.deleteById(id);
    }

    @Override
    public void deleteAssignmentDetailByEmployeeCodeAndClassId(String teacherCode, String classId) {
            assignmentDetailRepository.delete(assignmentDetailRepository.findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(teacherCode, classId));
    }

    @Override
    public List<TeacherLeaveNoteAndActiveHoursDto> findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours() {
        return assignmentDetailRepository.findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();
    }


    @Override //check integer between 1-12
    public List<TeacherAndTheirNumberOfClassesDto> findTeacherAndTheirNumberOfClassInAMonth(Integer month) throws InvalidMonth{
        if (month < 0 || month > 12) throw new InvalidMonth("Invalid month. Month should be from 1-12");
        return assignmentDetailRepository.findTeacherAndTheirNumberOfClass(month);
    }

    @Override //check integer between 1-12
    public List<TeacherAndTotalActiveHours> findTeachersAndTheirTotalActiveHoursInAMonth(Integer month) throws InvalidMonth{
        if (month < 0 || month > 12) throw new InvalidMonth("Invalid month. Month should be from 1-12");
        return assignmentDetailRepository.findTeachersAndTheirTotalActiveHoursInAMonth(month);
    }

    @Override
    public List<String> findTeacherListWhoHaveBeenPaidOrHaveNotBeenPaidInMonth(String isPaid, Integer month) throws InvalidMonth{
        if (month < 0 || month > 12) throw new InvalidMonth("Invalid month. Month should be from 1-12");
        return assignmentDetailRepository.findTeacherListWhoHaveBeenPairOrHaveNotBeenPaidInMonth(Boolean.parseBoolean(isPaid), month);
    }



}
