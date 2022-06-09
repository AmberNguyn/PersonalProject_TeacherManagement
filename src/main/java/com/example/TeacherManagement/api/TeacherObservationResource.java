package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.TeacherObservationService;
import com.example.TeacherManagement.service.dto.TeacherObservationDto;
import com.example.TeacherManagement.service.mapper.TeacherObservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
