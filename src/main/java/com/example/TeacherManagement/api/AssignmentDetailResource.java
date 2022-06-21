package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.AssignmentDetailDto;
import com.example.TeacherManagement.service.dto.TeacherAndTheirNumberOfClassesDto;
import com.example.TeacherManagement.service.dto.TeacherAndTotalActiveHoursDto;
import com.example.TeacherManagement.service.dto.TeacherLeaveNoteAndActiveHoursDto;
import com.example.TeacherManagement.service.mapper.AssignmentDetailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(AssignmentDetailResource.PATH)
@RequiredArgsConstructor
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

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<AssignmentDetailDto>> getAll() {
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDtos(assignmentDetailService.getAll()));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDetailDto> getById(@PathVariable("id") Integer id) {
        log.info("Searched id: {}", id);

        AssignmentDetail assignmentDetail = assignmentDetailService.findById(id)
                .orElseThrow(BusinessLogicException::AssignmentDetailIdNotFound);


        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetail));

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/find")
    public ResponseEntity<List<AssignmentDetailDto>> getAssignmentDetailByTeacherCodeAndStartDate(@RequestParam("teacherCode") String teacherCode,
                                                                                                  @RequestParam("startDate") String startDate) {
        List<AssignmentDetail> assignmentDetails = assignmentDetailService.findAssignmentDetailListByStartDateAfterAndEmployeeCode(LocalDate.parse(startDate), teacherCode);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDtos(assignmentDetails));
    }


//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/find-class")
    public ResponseEntity<AssignmentDetailDto> getAssignmentDetailByTeacherCodeAndClassId(@RequestParam("teacherCode") String teacherCode,
                                                                                          @RequestParam("classId") String classId) {

        log.info("Searched teacher code: {}", teacherCode);
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByTeacherCodeAndClassId(teacherCode, classId)
                .orElseThrow(BusinessLogicException::TeacherCodeOrClassIdNotFound);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetail));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AssignmentDetailDto> create(@RequestBody AssignmentDetailRequest assignmentDetailRequest) {
        AssignmentDetail assignmentDetail = assignmentDetailService.create(assignmentDetailRequest);

        return ResponseEntity
                .created(URI.create(AssignmentDetailResource.PATH + "/" + assignmentDetail.getId()))
                .body(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetailService.create(assignmentDetail)));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode,
                                       @RequestParam("classId") String classId) {
        log.info("Searched teacher code: {}", teacherCode);
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByTeacherCodeAndClassId(teacherCode, classId)
                .orElseThrow(BusinessLogicException::TeacherCodeOrClassIdNotFound);

        assignmentDetailService.deleteAssignmentDetailByEmployeeCodeAndClassId(teacherCode, classId);
        return ResponseEntity.noContent().build();

    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        log.info("Searched id: {}", id);
        AssignmentDetail assignmentDetail = assignmentDetailService.findById(id)
                .orElseThrow(BusinessLogicException::AssignmentDetailIdNotFound);
        assignmentDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AssignmentDetailDto> update(@PathVariable("id") Integer id,
                                                      @RequestBody AssignmentDetailRequest assignmentDetailRequest) {

        AssignmentDetail updatedAssignmentDetail = assignmentDetailService.update(assignmentDetailRequest, id);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(updatedAssignmentDetail));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/find-teachers-not-meet-active-hours")
    public ResponseEntity<List<TeacherLeaveNoteAndActiveHoursDto>> findTeachersWhoDoNotMeetTheRequiredHours() {
        return ResponseEntity.ok(assignmentDetailService.findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours());
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/number-of-classes-in-month/{month}")
    public ResponseEntity<List<TeacherAndTheirNumberOfClassesDto>> findTeacherAndTheirNumberOfClassInAMonth(@PathVariable("month") Integer month) {
        return ResponseEntity.ok(assignmentDetailService.findTeacherAndTheirNumberOfClassInAMonth(month));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/total-active-hours-in-month/{month}")
    public ResponseEntity<List<TeacherAndTotalActiveHoursDto>> findTeachersAndTotalActiveHoursInMonth(@PathVariable("month") Integer month) {
        return ResponseEntity.ok(assignmentDetailService.findTeachersAndTheirTotalActiveHoursInAMonth(month));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/paid-or-not-paid/{month}")
    public ResponseEntity<List<String>> findTeacherCodesWhoHaveOrHaveNotBeenPaidInMonth(@PathVariable("month") Integer month,
                                                                                        @RequestParam("isPaid") String isPaid) {
        return ResponseEntity.ok(assignmentDetailService.findTeacherListWhoHaveBeenPaidOrHaveNotBeenPaidInMonth(isPaid, month));
    }

}
