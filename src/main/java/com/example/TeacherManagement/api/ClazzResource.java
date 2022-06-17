package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.ClazzRequest;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.dto.ClazzDto;
import com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto;
import com.example.TeacherManagement.service.mapper.ClazzMapper;
import org.apache.coyote.Response;
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

    public static final String PATH = "/api/classes";

    @GetMapping
    public ResponseEntity<List<ClazzDto>> getAll() {
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDtos(clazzService.getAll()));
    }

    @GetMapping("/find")
    public ResponseEntity<ClazzDto> getClassByClassId(@RequestParam("classId") String classId) throws ResourceNotFoundException {
        Clazz clazz = clazzService.findClassByClassId(classId)
                .orElseThrow(() -> new ResourceNotFoundException(classId + " not found!"));
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDto(clazz));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClazzDto> getClassById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Clazz clazz = clazzService.findClassById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found"));
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDto(clazz));
    }

    @GetMapping("/classeshavenotbeenassigned")
    public ResponseEntity<List<ClazzHaveNotBeenAssignedDto>> findClassesHaveNotBeenAssigned()
    {
        return ResponseEntity.ok(clazzService.findClassesThatHaveNotBeenAssigned());
    }



    @PostMapping
    public ResponseEntity<ClazzDto> create(@RequestBody Clazz clazzRequest) {
        Clazz createdClazz = clazzService.addClass(
                new Clazz(
                        null,
                        clazzRequest.getClassId(),
                        clazzRequest.getNumberOfStudent(),
                        clazzRequest.getStartDate(),
                        clazzRequest.getEndDate(),
                        clazzRequest.getTotalCourseHours(),
                        clazzRequest.getCourseBook()
                )
        );
        return ResponseEntity.created(URI.create(ClazzResource.PATH + "/" + createdClazz.getId()))
                .body(ClazzMapper.INSTANCE.toDto(clazzService.addClass(createdClazz)));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("classId") String classId) throws ResourceNotFoundException {
        Clazz clazz = clazzService.findClassByClassId(classId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(classId + " not found!")
                );
        clazzService.deleteClassByClassId(classId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<ClazzDto> update(@RequestParam("classId") String clazzId,
                                           @RequestBody ClazzRequest clazzRequest) throws ResourceNotFoundException {

        Clazz editedClazz = clazzService.findClassByClassId(clazzId)
                .orElseThrow(() -> new ResourceNotFoundException(clazzId + " not found!"));

        editedClazz.setClassId(clazzRequest.getClassId());
        editedClazz.setNumberOfStudent(clazzRequest.getNumberOfStudent());
        editedClazz.setStartDate(clazzRequest.getStartDate());
        editedClazz.setEndDate(clazzRequest.getEndDate());
        editedClazz.setTotalCourseHours(clazzRequest.getTotalCourseHours());
        editedClazz.setCourseBook(clazzRequest.getCourseBook());

        Clazz updatedClazz = clazzService.addClass(editedClazz);
        return ResponseEntity.ok(ClazzMapper.INSTANCE.toDto(updatedClazz));
    }
}
