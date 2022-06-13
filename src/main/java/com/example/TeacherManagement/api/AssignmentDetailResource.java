package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.AssignmentDetailRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.entity.Room;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.RoomService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.AssignmentDetailDto;
import com.example.TeacherManagement.service.mapper.AssignmentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found!")
                );
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetail));
    }


    @PostMapping
    public ResponseEntity<AssignmentDetailDto> create(@RequestBody AssignmentDetailRequest assignmentDetailRequest) throws ResourceNotFoundException {
        Teacher requestTeacher = teacherService.findTeacherByEmployeeCode(assignmentDetailRequest.getTeacher_code())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getTeacher_code() + " not found"));

        Clazz requestClazz = clazzService.findClassByClassId(assignmentDetailRequest.getClazz_id())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getClazz_id() + " not found"));

        Room requestRoom = roomService.findRoomByRoomNumber(assignmentDetailRequest.getRoom_number())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getRoom_number() + " not found"));

        AssignmentDetail createdAssignmentDetail = assignmentDetailService.addAssignmentDetail(
                new AssignmentDetail(
                        null,
                        assignmentDetailRequest.getLesson(),
                        assignmentDetailRequest.getStartDate(),
                        assignmentDetailRequest.getWorkingDay(),
                        assignmentDetailRequest.isMorningShift(),
                        assignmentDetailRequest.isAfternoonShift(),
                        assignmentDetailRequest.isNightShift(),
                        assignmentDetailRequest.isTeachingStatus(),
                        assignmentDetailRequest.getActiveHours(),
                        assignmentDetailRequest.isObservationStatus(),
                        requestTeacher,
                        requestClazz,
                        requestRoom
                )
        );


        return ResponseEntity.created(URI.create(AssignmentDetailResource.PATH + "/" + createdAssignmentDetail.getId()))
                .body(AssignmentDetailMapper.INSTANCE.toDto(assignmentDetailService.addAssignmentDetail(createdAssignmentDetail)));
    }


    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode,
                                       @RequestParam("startDate") LocalDate startDate) throws ResourceNotFoundException {
        AssignmentDetail assignmentDetail = assignmentDetailService.findAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found!")
                );
        assignmentDetailService.deleteAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/")
    public ResponseEntity<AssignmentDetailDto> update(@RequestParam("teacherCode") String teacherCode,
                                                      @RequestParam("startDate") LocalDate startDate,
                                                      @RequestBody AssignmentDetailRequest assignmentDetailRequest) throws ResourceNotFoundException {
        AssignmentDetail editedAssignmentDetail = assignmentDetailService.findAssignmentDetailByEmployeeCodeAndStartDate(teacherCode, startDate)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + "'s assignment detail not found"));
        Teacher teachRequest = teacherService.findTeacherByEmployeeCode(assignmentDetailRequest.getTeacher_code())
                .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getTeacher_code() + " not found!"));

        Room roomRequest = roomService.findRoomByRoomNumber(assignmentDetailRequest.getRoom_number())
                        .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getRoom_number() + " not found!"));
        Clazz classRequest = clazzService.findClassByClassId(assignmentDetailRequest.getClazz_id())
                        .orElseThrow(() -> new ResourceNotFoundException(assignmentDetailRequest.getClazz_id() + " not found!"));


        editedAssignmentDetail.setLesson(assignmentDetailRequest.getLesson());
        editedAssignmentDetail.setStartDate(assignmentDetailRequest.getStartDate());
        editedAssignmentDetail.setWorkingDay(assignmentDetailRequest.getWorkingDay());
        editedAssignmentDetail.setMorningShift(assignmentDetailRequest.isMorningShift());
        editedAssignmentDetail.setTeachingStatus(assignmentDetailRequest.isAfternoonShift());
        editedAssignmentDetail.setNightShift(assignmentDetailRequest.isNightShift());
        editedAssignmentDetail.setTeachingStatus(assignmentDetailRequest.isTeachingStatus());
        editedAssignmentDetail.setActiveHours(assignmentDetailRequest.getActiveHours());
        editedAssignmentDetail.setTeachingStatus(assignmentDetailRequest.isTeachingStatus());

        AssignmentDetail updatedAssignmentDetail = assignmentDetailService.addAssignmentDetail(editedAssignmentDetail);
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDto(updatedAssignmentDetail));
    }


}
