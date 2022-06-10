package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.TeacherObservation;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.TeacherObservationService;
import com.example.TeacherManagement.service.dto.TeacherObservationDto;
import com.example.TeacherManagement.service.mapper.TeacherObservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(TeacherObservationResource.PATH)
public class TeacherObservationResource {
    @Autowired
    private TeacherObservationService teacherObservationService;

    public static final String PATH = "/api/teacherObservation";

    @GetMapping
    public ResponseEntity<List<TeacherObservationDto>> getAll() {
        return ResponseEntity.ok(TeacherObservationMapper.INSTANCE.toDtos(teacherObservationService.getAll()));
    }

    @GetMapping("/{teacherCode}/{observationDate}")
    public ResponseEntity<TeacherObservationDto> getTeacherObservationByTeacherCode(@PathVariable String teacherCode, @PathVariable LocalDate observationDate) throws ResourceNotFoundException {
        TeacherObservation teacherObservation = teacherObservationService.findTeacherObservationByEmployeeCodeAndObservationDate(teacherCode, observationDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s observation report not found!")
                );
        return ResponseEntity.ok(TeacherObservationMapper.INSTANCE.toDto(teacherObservation));
    }

    @PostMapping
    public ResponseEntity<TeacherObservationDto> create(@RequestBody TeacherObservation teacherObservation) {
        TeacherObservation createdTeacherObservation = teacherObservationService.addTeacherObservation(teacherObservation);
        return ResponseEntity.created(URI.create(TeacherObservationResource.PATH + "/" + createdTeacherObservation.getAssignmentDetail().getTeacher().getEmployeeCode()))
                .body(TeacherObservationMapper.INSTANCE.toDto(createdTeacherObservation));
    }

    @DeleteMapping("/{teacherCode}/{observationDate}")
    public ResponseEntity<Void> delete(@PathVariable String teacherCode, @PathVariable LocalDate observationDate) throws ResourceNotFoundException {
        TeacherObservation teacherObservation = teacherObservationService.findTeacherObservationByEmployeeCodeAndObservationDate(teacherCode, observationDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s observation report not found")
                );
        teacherObservationService.deleteTeacherObservationByEmployeeCodeAndObservationDate(teacherCode, observationDate);
        return ResponseEntity.noContent().build();
    }

}
