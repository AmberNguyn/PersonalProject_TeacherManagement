package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.repository.AssignmentDetailRepository;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHoursDto;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Slf4j
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
    public final TeacherService teacherService;


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
    public AssignmentDetail create(AssignmentDetailRequest assignmentDetailRequest) {
        log.info("Searching classId: {}", assignmentDetailRequest.getClassId());
        Clazz requestClazz = clazzService.findByClassId(assignmentDetailRequest.getClassId())
                .orElseThrow(BusinessLogicException::ClassCodeNotFound);

        log.info("Searching contractId: {}", assignmentDetailRequest.getContractId());
        Contract requestContract = contractService.findByContractId(assignmentDetailRequest.getContractId())
                .orElseThrow(BusinessLogicException::ContractIdNotFound);

        if (requestContract.getEndDate().isAfter(LocalDate.now())) {
            AssignmentDetail createdAssignmentDetail = new AssignmentDetail();
            createdAssignmentDetail.setCourseStartDate(assignmentDetailRequest.getCourseStartDate());
            createdAssignmentDetail.setCourseEndDate(assignmentDetailRequest.getCourseEndDate());
            createdAssignmentDetail.setActiveHours(assignmentDetailRequest.getActiveHours());
            createdAssignmentDetail.setLeaveNote(assignmentDetailRequest.getLeaveNote());
            createdAssignmentDetail.setPayRate(assignmentDetailRequest.getPayRate());
            createdAssignmentDetail.setExpectedHours(assignmentDetailRequest.getExpectedHours());
            createdAssignmentDetail.setContract(requestContract);
            createdAssignmentDetail.setClazz(requestClazz);

            return assignmentDetailRepository.save(createdAssignmentDetail);
        } else {
            throw BusinessLogicException.badRequest("ContractExpired", "Contract Expired");
        }

    }

    @Override
    public AssignmentDetail update(AssignmentDetailRequest assignmentDetailRequest, Integer id) {
        log.info("Search assignment detail id: {}", id);
        AssignmentDetail editedAssignmentDetail = assignmentDetailRepository.findById(id)
                .orElseThrow(BusinessLogicException::AssignmentDetailIdNotFound);

        log.info("Searched class id: {}", assignmentDetailRequest.getClassId());
        Clazz requestClazz = clazzService.findByClassId(assignmentDetailRequest.getClassId())
                .orElseThrow(BusinessLogicException::ClassCodeNotFound);

        log.info("Searched contract id: {}", assignmentDetailRequest.getContractId());
        Contract requestContract = contractService.findByContractId(assignmentDetailRequest.getContractId())
                .orElseThrow(BusinessLogicException::ContractIdNotFound);

        editedAssignmentDetail.setCourseStartDate(assignmentDetailRequest.getCourseStartDate());
        editedAssignmentDetail.setCourseEndDate(assignmentDetailRequest.getCourseEndDate());
        editedAssignmentDetail.setActiveHours(assignmentDetailRequest.getActiveHours());
        editedAssignmentDetail.setLeaveNote(assignmentDetailRequest.getLeaveNote());
        editedAssignmentDetail.setPayRate(assignmentDetailRequest.getPayRate());
        editedAssignmentDetail.setClazz(requestClazz);
        editedAssignmentDetail.setContract(requestContract);

        return assignmentDetailRepository.save(editedAssignmentDetail);
    }


    @Override
    public List<AssignmentDetail> findAssignmentDetailListByStartDateAfterAndEmployeeCode(LocalDate startDate, String teacherCode) {
        return assignmentDetailRepository.findAssignmentDetailByCourseStartDateAfterAndContractTeacherEmployeeCodeContaining(startDate, teacherCode);
    }

    @Override
    public Optional<AssignmentDetail> findAssignmentDetailByTeacherCodeAndClassId(String teacherCode, String classId) {
            teacherService.findByEmployeeCode(teacherCode).orElseThrow(BusinessLogicException::TeacherCodeNotFound);
            clazzService.findByClassId(classId).orElseThrow(BusinessLogicException::ClassCodeNotFound);
            return assignmentDetailRepository.findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(teacherCode, classId);

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
        if (assignmentDetailRepository.findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(teacherCode, classId).isPresent())
        {
            assignmentDetailRepository.delete(assignmentDetailRepository.findAssignmentDetailByContractTeacherEmployeeCodeContainingAndClazzClassIdContaining(teacherCode, classId).get());
        }

    }

    @Override
    public List<TeacherLeaveNoteAndActiveHoursDto> findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours() {
        return assignmentDetailRepository.findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();
    }


    @Override
    public List<TeacherAndTheirNumberOfClassesDto> findTeacherAndTheirNumberOfClassInAMonth(Integer month) {
        log.info("Invalid month: {}", month);
        return assignmentDetailRepository.findTeacherAndTheirNumberOfClass(month);
    }

    @Override
    public List<TeacherAndTotalActiveHoursDto> findTeachersAndTheirTotalActiveHoursInAMonth(Integer month) {
        log.info("Invalid month: {}", month);
        return assignmentDetailRepository.findTeachersAndTheirTotalActiveHoursInAMonth(month);
    }

    @Override
    public List<String> findTeacherListWhoHaveBeenPaidOrHaveNotBeenPaidInMonth(String isPaid, Integer month) {
        log.info("Invalid month: {}", month);

        return assignmentDetailRepository.findTeacherListWhoHaveBeenPairOrHaveNotBeenPaidInMonth(Boolean.parseBoolean(isPaid), month);
    }

    @Override
    public Integer findIncomeBeforeTaxByAssignmentDetailIdUsingExpectedHours(Integer id) {
        return assignmentDetailRepository.findIncomeBeforeTaxByAssignmentDetailIdUsingExpectedHours(id);
    }

    @Override
    public Integer findIncomeTaxByAssignmentDetailIdUsingExpectedHours(Integer id) {
        return assignmentDetailRepository.findIncomeTaxByAssignmentDetailIdUsingExpectedHours(id);
    }

    @Override
    public Integer findTransferredAmountByAssignmentDetailIdUsingExpected(Integer id) {
        return assignmentDetailRepository.findTransferredAmountByAssignmentDetailIdUsingExpectedHours(id);
    }

    @Override
    public Integer findIncomeBeforeTaxByAssignmentDetailIdUsingActiveHours(Integer id) {
        return assignmentDetailRepository.findIncomeBeforeTaxByAssignmentDetailIdUsingActiveHours(id);
    }

    @Override
    public Integer findTaxByAssignmentDetailIdUsingActiveHours(Integer id) {
        return assignmentDetailRepository.findTaxByAssignmentDetailIdUsingActiveHours(id);
    }

    @Override
    public Integer findTransferredAmountByAssignmentDetailIdUsingActiveHours(Integer id) {
        return assignmentDetailRepository.findTransferredAmountByAssignmentDetailIdUsingActiveHours(id);
    }


}
