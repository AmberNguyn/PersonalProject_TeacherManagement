package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.Class;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.ClassService;
import com.example.TeacherManagement.service.dto.ClassDto;
import com.example.TeacherManagement.service.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{classId}")
    public ResponseEntity<ClassDto> getClassByClassId(@PathVariable String classId) throws ResourceNotFoundException {
        Class clazz = classService.findClassByClassId(classId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(classId + " not found!")
                );
        return ResponseEntity.ok(ClassMapper.INSTANCE.toDto(clazz));
    }

    @PostMapping
    public ResponseEntity<ClassDto> create(@RequestBody Class clazz) {
        Class createdClazz = classService.addClass(clazz);
        return ResponseEntity.created(URI.create(ClassResource.PATH + "/" + createdClazz.getClassId()))
                .body(ClassMapper.INSTANCE.toDto(createdClazz));
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> delete(@PathVariable String classId) throws ResourceNotFoundException {
        Class clazz = classService.findClassByClassId(classId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(classId + " not found!")
                );
        classService.deleteClassByClassId(classId);
        return ResponseEntity.noContent().build();
    }
}
