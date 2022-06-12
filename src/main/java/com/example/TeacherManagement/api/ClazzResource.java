package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.dto.ClazzDto;
import com.example.TeacherManagement.service.mapper.ClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(ClazzResource.PATH)

public class ClazzResource {
    @Autowired
    private ClazzService clazzService;

    public static final String PATH = "/api/class";

    @GetMapping
    public ResponseEntity<List<ClazzDto>> getAll() {
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDtos(clazzService.getAll()));
    }

    @GetMapping("/{classId}")
    public ResponseEntity<ClazzDto> getClassByClassId(@PathVariable String classId) throws ResourceNotFoundException {
        Clazz clazz = clazzService.findClassByClassId(classId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(classId + " not found!")
                );
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDto(clazz));
    }

    @PostMapping
    public ResponseEntity<ClazzDto> create(@RequestBody Clazz clazz) {
        Clazz createdClazz = clazzService.addClass(clazz);
        return ResponseEntity.created(URI.create(ClazzResource.PATH + "/" + createdClazz.getClassId()))
                .body(ClazzMapper.INSTANCE.toDto(createdClazz));
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> delete(@PathVariable String classId) throws ResourceNotFoundException {
        Clazz clazz = clazzService.findClassByClassId(classId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(classId + " not found!")
                );
        clazzService.deleteClassByClassId(classId);
        return ResponseEntity.noContent().build();
    }
}
