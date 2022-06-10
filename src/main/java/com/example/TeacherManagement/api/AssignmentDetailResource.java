package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClassService;
import com.example.TeacherManagement.service.RoomService;
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
    private RoomService roomService;
    @Autowired
    private ClassService classService;

    public static final String PATH = "/api/assignmentDetail";

    @GetMapping
    public ResponseEntity<List<AssignmentDetailDto>> getAll() {
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDtos(assignmentDetailService.getAll()));
    }

    @GetMapping("/{teacherCode}/{startDate}")
    public ResponseEntity<AssignmentDetailDto> getAssignmentDetailByTeacherCode(@PathVariable String teacherCode, @PathVariable LocalDate startDate)
        throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found!")
                );
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetail));
    }

//    @PostMapping
//    public ResponseEntity<AssignmentDetailDto> create(@RequestBody AssignmentDetailRequest assignmentDetail){
//        AssignmentDetail createdAssignmentDetail = assignmentDetailService.addAssignmentDetail(
//                new AssignmentDetailRequest(
//                        teacherService.findTeacherByEmployeeCode(assignmentDetail.getTeacherCode())
//                                .orElseThrow(()->ResourceNotFoundException()))
//,
//                        assignmentDetail.getLesson(),
//                        assignmentDetail.getStartDate(),
//                        assignmentDetail.isMorningShift(),
//                        assignmentDetail.isAfternoonShift(),
//                        assignmentDetail.isNightShift(),
//                        assignmentDetail.getWeekday(),
//
//                        assignmentDetail.getRoomNumber(),
//
//                        assignmentDetail.getClassId(),
//                );
//
//        return ResponseEntity.created(URI.create(AssignmentDetailResource.PATH + "/" + createdAssignmentDetail.getTeacher().getEmployeeCode()))
//                .body(AssignmentDetailMapper.INSTANCE.toDto(createdAssignmentDetail));
//    }

    @DeleteMapping("/{teacherCode}/{startDate}")
    public ResponseEntity<Void> delete(@PathVariable String teacherCode, @PathVariable LocalDate startDate) throws ResourceNotFoundException
    {
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found!")
                );
        assignmentDetailService.deleteAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{teacherCode}/{startDate}")
//    public ResponseEntity<AssignmentDetailDto> update(@PathVariable String teacherCode,
//                                                      @PathVariable LocalDate startDate,
//                                                      @RequestBody AssignmentDetail newAssignmentDetail) throws ResourceNotFoundException {
//        AssignmentDetail editedAssignmentDetail = assignmentDetailService.findAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate)
//                .orElseThrow(
//                        () -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found")
//                );
//        editedAssignmentDetail.setLesson(newAssignmentDetail.getLesson());
//        editedAssignmentDetail.setStartDate(newAssignmentDetail.getStartDate());
//        editedAssignmentDetail.setWorkingDay(newAssignmentDetail.getWorkingDay());
//        editedAssignmentDetail.setMorningShift(newAssignmentDetail.isMorningShift());
//        editedAssignmentDetail.setTeachingStatus(newAssignmentDetail.isTeachingStatus());
//        editedAssignmentDetail.setTeacher(newAssignmentDetail.);
//
//    }


}
