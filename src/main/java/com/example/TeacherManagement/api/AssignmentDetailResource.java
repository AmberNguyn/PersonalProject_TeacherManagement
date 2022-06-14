package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.AssignmentDetailDto;
import com.example.TeacherManagement.service.mapper.AssignmentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
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

    //check how to input in postman
    @GetMapping("/find")
    public ResponseEntity<AssignmentDetailDto> getAssignmentDetailByTeacherCode(@RequestParam("teacherCode") String teacherCode,
                                                                                @RequestParam("startDate") LocalDate startDate)
            throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByStartDateAndEmployeeCode(teacherCode, startDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found!")
                );
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetail));
    }


    @PostMapping
    public ResponseEntity<AssignmentDetailDto> create(@RequestBody AssignmentDetailRequest assignmentDetailRequest) throws ResourceNotFoundException {

        Clazz requestClazz = clazzService.findClassByClassId(assignmentDetailRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getClassId() + " not found"));

        Contract requestContract = contractService.findContractByContractId(assignmentDetailRequest.getContractId())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getContractId() + " not found!"));

        AssignmentDetail createdAssignmentDetail = assignmentDetailService.addAssignmentDetail(
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

        return ResponseEntity.created(URI.create(AssignmentDetailResource.PATH + "/" + createdAssignmentDetail.getId()))
                .body(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetailService.addAssignmentDetail(createdAssignmentDetail)));
    }


    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode,
                                       @RequestParam("startDate") LocalDate startDate) throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByStartDateAndEmployeeCode(teacherCode, startDate)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found!"));
        assignmentDetailService.deleteAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/")
    public ResponseEntity<AssignmentDetailDto> update(@RequestParam("teacherCode") String teacherCode,
                                                      @RequestParam("startDate") LocalDate startDate,
                                                      @RequestBody AssignmentDetailRequest assignmentDetailRequest) throws ResourceNotFoundException {
        AssignmentDetail editedAssignmentDetail = assignmentDetailService.findAssignmentDetailByStartDateAndEmployeeCode(teacherCode, startDate)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found"));

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

        AssignmentDetail updatedAssignmentDetail = assignmentDetailService.addAssignmentDetail(editedAssignmentDetail);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(updatedAssignmentDetail));
    }


}
