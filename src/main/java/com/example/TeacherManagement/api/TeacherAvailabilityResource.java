package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.TeacherAvailability;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.TeacherAvailabilityService;
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

    public static final String PATH = "api/teacherAvailability";

    @GetMapping
    public ResponseEntity<List<TeacherAvailabilityDto>> getAll() {
        return ResponseEntity.ok(TeacherAvailabilityMapper.INSTANCE.toDtos(teacherAvailabilityService.getAll()));
    }

    @GetMapping("/{teacherCode}")
    public ResponseEntity<TeacherAvailabilityDto> getTeacherAvailabilityByTeacherCode(@PathVariable String teacherCode) throws ResourceNotFoundException {
        TeacherAvailability teacherAvailability = teacherAvailabilityService.findTeacherAvailabilityByEmployeeCode(teacherCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s availability sheet not found!")
                );
        return ResponseEntity.ok(TeacherAvailabilityMapper.INSTANCE.toDto(teacherAvailability));
    }

    @PostMapping
    public ResponseEntity<TeacherAvailabilityDto> create(@RequestBody TeacherAvailability teacherAvailability) {
        TeacherAvailability createTeacherAvailability = teacherAvailabilityService.addTeacherAvailability(teacherAvailability);
        return ResponseEntity.created(URI.create(TeacherAvailabilityResource.PATH + "/" + createTeacherAvailability.getTeacher().getEmployeeCode()))
                .body(TeacherAvailabilityMapper.INSTANCE.toDto(createTeacherAvailability));
    }

    @DeleteMapping("/{teacherCode}")
    public ResponseEntity<Void> delete(@PathVariable String teacherCode) throws ResourceNotFoundException {
        TeacherAvailability teacherAvailability = teacherAvailabilityService.findTeacherAvailabilityByEmployeeCode(teacherCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s availability sheet not found!")
                );
        teacherAvailabilityService.deleteTeacherAvailabilityByEmployeeCode(teacherCode);
        return ResponseEntity.noContent().build();
    }
}
