package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.exception.InvalidMonth;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.AssignmentDetailDto;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHours;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;
import com.example.TeacherManagement.service.mapper.AssignmentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(AssignmentDetailResource.PATH)

public class AssignmentDetailResource {

    @Autowired
    private AssignmentDetailService assignmentDetailService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private ClazzService clazzService;

    public static final String PATH = "/api/assignmentDetails";

    @GetMapping
    public ResponseEntity<List<AssignmentDetailDto>> getAll() {
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDtos(assignmentDetailService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDetailDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found!"));
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetail));
    }


    @GetMapping("/find")
    public ResponseEntity<List<AssignmentDetailDto>> getAssignmentDetailByTeacherCode(@RequestParam("teacherCode") String teacherCode,
                                                                                      @RequestParam("startDate") String startDate)
            throws ResourceNotFoundException {
        List<AssignmentDetail> assignmentDetails = assignmentDetailService.findAssignmentDetailListByStartDateAfterAndEmployeeCode(LocalDate.parse(startDate), teacherCode);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDtos(assignmentDetails));
    }


    @GetMapping("/find-class")
    public ResponseEntity<AssignmentDetailDto> getAssignmentDetailByTeacherCodeAndClassId(@RequestParam("teacherCode") String teacherCode,
                                                                                          @RequestParam("classId") String classId) throws ResourceNotFoundException {

        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByTeacherCodeAndClassId(teacherCode, classId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment detail of " + teacherCode + " and " + classId + " not found!"));
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetail));
    }


    @PostMapping
    public ResponseEntity<AssignmentDetailDto> create(@RequestBody AssignmentDetailRequest assignmentDetailRequest) throws ResourceNotFoundException {
            AssignmentDetail assignmentDetail = assignmentDetailService.create(assignmentDetailRequest);

        return ResponseEntity.created(URI.create(AssignmentDetailResource.PATH + "/" + assignmentDetail.getId()))
                .body(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetailService.create(assignmentDetail)));
    }


    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode,
                                       @RequestParam("classId") String classId) throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByTeacherCodeAndClassId(teacherCode, classId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment detail of " + teacherCode + " and " + classId + " not found!"));

        assignmentDetailService.deleteAssignmentDetailByEmployeeCodeAndClassId(teacherCode, classId);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found!"));
        assignmentDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/")
    public ResponseEntity<AssignmentDetailDto> update(@RequestParam("teacherCode") String teacherCode,
                                                      @RequestParam("classId") String classId,
                                                      @RequestBody AssignmentDetailRequest assignmentDetailRequest) throws ResourceNotFoundException {
        AssignmentDetail editedAssignmentDetail = assignmentDetailService.findAssignmentDetailByTeacherCodeAndClassId(teacherCode, classId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment detail of " + teacherCode + " and " + classId + " not found!"));

        Clazz requestClazz = clazzService.findClassByClassId(assignmentDetailRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getClassId() + " not found"));

        Contract requestContract = contractService.findContractByContractId(assignmentDetailRequest.getContractId())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getContractId() + " not found!"));


        editedAssignmentDetail.setCourseStartDate(assignmentDetailRequest.getCourseStartDate());
        editedAssignmentDetail.setCourseEndDate(assignmentDetailRequest.getCourseEndDate());
        editedAssignmentDetail.setExpectedHours(assignmentDetailRequest.getExpectedHours());
        editedAssignmentDetail.setActiveHours(assignmentDetailRequest.getActiveHours());
        editedAssignmentDetail.setLeaveNote(assignmentDetailRequest.getLeaveNote());
        editedAssignmentDetail.setPayRate(assignmentDetailRequest.getPayRate());
        editedAssignmentDetail.setClazz(requestClazz);
        editedAssignmentDetail.setContract(requestContract);

        AssignmentDetail updatedAssignmentDetail = assignmentDetailService.create(editedAssignmentDetail);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(updatedAssignmentDetail));
    }


    @GetMapping("/active-hours")
    public ResponseEntity<List<TeacherLeaveNoteAndActiveHoursDto>> findTeachersWhoDoNotMeetTheRequiredHours() {
        return ResponseEntity.ok(assignmentDetailService.findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours());
    }

    @GetMapping("/number-of-classes-in-month/{month}")
    public ResponseEntity<List<TeacherAndTheirNumberOfClassesDto>> findTeacherAndTheirNumberOfClassInAMonth(@PathVariable("month") Integer month) throws InvalidMonth {
        return ResponseEntity.ok(assignmentDetailService.findTeacherAndTheirNumberOfClassInAMonth(month));
    }

    @GetMapping("/total-active-hours-in-month/{month}")
    public ResponseEntity<List<TeacherAndTotalActiveHours>> findTeachersAndTotalActiveHoursInMonth(@PathVariable("month") Integer month) throws InvalidMonth {
        return ResponseEntity.ok(assignmentDetailService.findTeachersAndTheirTotalActiveHoursInAMonth(month));
    }

    @GetMapping("/paid-or-not-paid/{month}")
    public ResponseEntity<List<String>> findTeacherCodesWhoHaveOrHaveNotBeenPaidInMonth(@PathVariable("month") Integer month,
                                                                                        @RequestParam("isPaid") String isPaid) throws InvalidMonth {
        return ResponseEntity.ok(assignmentDetailService.findTeacherListWhoHaveBeenPaidOrHaveNotBeenPaidInMonth(isPaid, month));
    }

}
