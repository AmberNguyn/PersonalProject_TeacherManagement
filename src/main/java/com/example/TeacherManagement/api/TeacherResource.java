package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.TeacherDto;
import com.example.TeacherManagement.service.mapper.TeacherAvailabilityMapper;
import com.example.TeacherManagement.service.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(TeacherResource.PATH)
public class TeacherResource {
    @Autowired
    private TeacherService teacherService;

    public static final String PATH = "/api/teacher";

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDtos(teacherService.getAll()));
    }
}
