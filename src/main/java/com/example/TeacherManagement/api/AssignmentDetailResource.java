package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.AssignmentDetailDto;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;
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

    //find list of assignment detail
    @GetMapping("/find")
    public ResponseEntity<List<AssignmentDetailDto>> getAssignmentDetailByTeacherCode(@RequestParam("teacherCode") String teacherCode,
                                                                                      @RequestParam("startDate") String startDate)
            throws ResourceNotFoundException {
        List<AssignmentDetail> assignmentDetails = assignmentDetailService.findAssignmentDetailListByStartDateAfterAndEmployeeCode(LocalDate.parse(startDate), teacherCode);
        if (assignmentDetails.size() == 0)
            throw new ResourceNotFoundException("No assignment detail of " + teacherCode + " found");

        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDtos(assignmentDetails));


    }

    //find assignment detail of a teacher and a class id
    @GetMapping("/findClass")
    public ResponseEntity<AssignmentDetailDto> getAssignmentDetailByTeacherCodeAndClassId(@RequestParam("teacherCode") String teacherCode,
                                                                                          @RequestParam("classId") String classId) throws ResourceNotFoundException {

        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByTeacherCodeAndClassId(teacherCode, classId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment detail of " + teacherCode + " and " + classId + " not found!"));

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
                                       @RequestParam("classId") String classId) throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByTeacherCodeAndClassId(teacherCode, classId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment detail of " + teacherCode + " and " + classId + " not found!"));

        assignmentDetailService.deleteAssignmentDetailByEmployeeCodeAndClassId(teacherCode, classId);
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

        AssignmentDetail updatedAssignmentDetail = assignmentDetailService.addAssignmentDetail(editedAssignmentDetail);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(updatedAssignmentDetail));
    }


    // ------------ TEST POSTMAN------------
    @GetMapping("/activeHours")
    public ResponseEntity<List<TeacherLeaveNoteAndActiveHoursDto>> findTeachersWhoDoNotMeetTheRequiredHours() throws ResourceNotFoundException {
        List<TeacherLeaveNoteAndActiveHoursDto> teachers = assignmentDetailService.findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours();
        if (teachers.size() == 0)
            throw new ResourceNotFoundException("Teachers who do not meet the required hours not found");

        return ResponseEntity.ok(teachers);

    }
}
