package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.TeacherAvailabilityService;
import com.example.TeacherManagement.service.dto.TeacherAvailabilityDto;
import com.example.TeacherManagement.service.mapper.TeacherAvailabilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
