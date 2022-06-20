package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.ClazzRequest;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.dto.ClazzDto;
import com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto;
import com.example.TeacherManagement.service.mapper.ClazzMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(ClazzResource.PATH)

public class ClazzResource {
    @Autowired
    private ClazzService clazzService;

    public static final String PATH = "/api/classes";

    @GetMapping
    public ResponseEntity<List<ClazzDto>> getAll() {
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDtos(clazzService.getAll()));
    }

    @GetMapping("/find")
    public ResponseEntity<ClazzDto> getClassByClassId(@RequestParam("classId") String classId) {

        Clazz clazz = clazzService.findByClassId(classId)
                .orElseThrow(BusinessLogicException::ClassCodeNotFound);
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDto(clazz));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClazzDto> getClassById(@PathVariable("id") Integer id) {
        Clazz clazz = clazzService.findById(id)
                .orElseThrow(BusinessLogicException::ClassIdNotFound);
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDto(clazz));
    }

    @GetMapping("/classes-have-not-been-assigned")
    public ResponseEntity<List<ClazzHaveNotBeenAssignedDto>> findClassesHaveNotBeenAssigned() {
        return ResponseEntity.ok(clazzService.findClassesThatHaveNotBeenAssigned());
    }


    @PostMapping
    public ResponseEntity<ClazzDto> create(@RequestBody Clazz clazzRequest) {
        Clazz createdClazz = clazzService.create(clazzRequest);

        return ResponseEntity
                .created(URI.create(ClazzResource.PATH + "/" + createdClazz.getId()))
                .body(ClazzMapper.INSTANCE.toDto(clazzService.create(createdClazz)));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByClassCode(@RequestParam("classId") String classCode) {
        Clazz clazz = clazzService.findByClassId(classCode)
                .orElseThrow(BusinessLogicException::ClassCodeNotFound);
        clazzService.deleteByClassId(classCode);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Clazz clazz = clazzService.findById(id)
                .orElseThrow(BusinessLogicException::ClassIdNotFound);
        clazzService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClazzDto> update(@PathVariable("id") Integer id,
                                           @RequestBody ClazzRequest clazzRequest) {

        Clazz updatedClazz = clazzService.update(clazzRequest, id);
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDto(updatedClazz));
    }
}
