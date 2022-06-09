package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.ClassService;
import com.example.TeacherManagement.service.dto.ClassDto;
import com.example.TeacherManagement.service.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(ClassResource.PATH)

public class ClassResource {
    @Autowired
    private ClassService classService;

    public static final String PATH = "/api/class";

    @GetMapping
    public ResponseEntity<List<ClassDto>> getAll() {
        return ResponseEntity.ok(ClassMapper.INSTANCE.toDtos(classService.getAll()));
    }
}
