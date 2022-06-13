package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.TeacherAvailabilityRequest;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.entity.TeacherAvailability;
import com.example.TeacherManagement.entity.WorkingDay;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.TeacherAvailabilityService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.TeacherAvailabilityDto;
import com.example.TeacherManagement.service.mapper.TeacherAvailabilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(TeacherAvailabilityResource.PATH)
public class TeacherAvailabilityResource {
    @Autowired
    private TeacherAvailabilityService teacherAvailabilityService;
    @Autowired
    private TeacherService teacherService;

    public static final String PATH = "/api/teacherAvailabilities";

    @GetMapping
    public ResponseEntity<List<TeacherAvailabilityDto>> getAll() {
        return ResponseEntity.ok(TeacherAvailabilityMapper.INSTANCE.toDtos(teacherAvailabilityService.getAll()));
    }

    @GetMapping("/find")
    public ResponseEntity<List<TeacherAvailabilityDto>> getTeacherAvailabilityByTeacherCode(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
        List<TeacherAvailability> teacherAvailability = teacherAvailabilityService.findTeacherAvailabilityByEmployeeCode(teacherCode);
        if (teacherAvailability.size() == 0) throw new ResourceNotFoundException(teacherCode + " not found");
        else {
            return ResponseEntity.ok(TeacherAvailabilityMapper.INSTANCE.toDtos(teacherAvailability));
        }

    }


    @PostMapping
    public ResponseEntity<TeacherAvailabilityDto> create(@RequestBody TeacherAvailabilityRequest teacherAvailabilityRequest) throws ResourceNotFoundException {
        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(teacherAvailabilityRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException(teacherAvailabilityRequest.getTeacherCode() + " not found!"));


        TeacherAvailability createTeacherAvailability = teacherAvailabilityService.addTeacherAvailability(
                new TeacherAvailability(
                        null,
                        teacherAvailabilityRequest.getStartDate(),
                        teacherAvailabilityRequest.getWorkingDay(),
                        teacherAvailabilityRequest.isMorningShift(),
                        teacherAvailabilityRequest.isAfternoonShift(),
                        teacherAvailabilityRequest.isNightShift(),
                        teacherRequest
                )
        );
        return ResponseEntity.created(URI.create(TeacherAvailabilityResource.PATH + "/" + createTeacherAvailability.getId()))
                .body(TeacherAvailabilityMapper.INSTANCE.toDto(teacherAvailabilityService.addTeacherAvailability(createTeacherAvailability)));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteAll(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
        List<TeacherAvailability> teacherAvailability = teacherAvailabilityService.findTeacherAvailabilityByEmployeeCode(teacherCode);
        if (teacherAvailability.size() == 0) throw new ResourceNotFoundException(teacherCode + " not found");
        else {
            teacherAvailabilityService.deleteTeacherAvailabilityListByEmployeeCode(teacherCode);
            return ResponseEntity.noContent().build();
        }

    }

    @PutMapping("/")
    public ResponseEntity<TeacherAvailabilityDto> update(@RequestParam("teacherCode") String teacherCode,
                                                         @RequestParam("workingDay") WorkingDay workingDay,
                                                         @RequestBody TeacherAvailabilityRequest teacherAvailabilityRequest) throws ResourceNotFoundException {

            TeacherAvailability editedTeacherAvailability = teacherAvailabilityService.findTeacherAvailabilityByEmployeeCodeAndWorkingDay(teacherCode, workingDay);

            Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(teacherAvailabilityRequest.getTeacherCode())
                            .orElseThrow(() -> new ResourceNotFoundException(teacherAvailabilityRequest.getTeacherCode() + " not found!"));


            editedTeacherAvailability.setStartDate(teacherAvailabilityRequest.getStartDate());
            editedTeacherAvailability.setWorkingDay(teacherAvailabilityRequest.getWorkingDay());
            editedTeacherAvailability.setMorningShift(teacherAvailabilityRequest.isMorningShift());
            editedTeacherAvailability.setAfternoonShift(teacherAvailabilityRequest.isAfternoonShift());
            editedTeacherAvailability.setNightShift(teacherAvailabilityRequest.isNightShift());

            TeacherAvailability updatedTeacherAvailability = teacherAvailabilityService.addTeacherAvailability(editedTeacherAvailability);
            return ResponseEntity.ok(TeacherAvailabilityMapper.INSTANCE.toDto(editedTeacherAvailability));

    }
}
