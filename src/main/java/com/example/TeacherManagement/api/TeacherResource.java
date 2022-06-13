package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.TeacherRequest;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.TeacherDto;
import com.example.TeacherManagement.service.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

//    @GetMapping
//    public ResponseEntity<List<Teacher>> getAll() {
//        return ResponseEntity.ok(teacherService.getAll());
//    }

    @GetMapping("/{teacherCode}")
    public ResponseEntity<TeacherDto> getTeacherByTeacherCode(@PathVariable String teacherCode) throws ResourceNotFoundException {
        Teacher teacher = teacherService.findTeacherByEmployeeCode(teacherCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + " not found!")
                );
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDto(teacher));
    }


    @PostMapping
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherRequest teacherRequest) {
        Teacher createdTeacher = teacherService.addTeacher(
                new Teacher(
                        null,
                        teacherRequest.getEmployeeCode(),
                        teacherRequest.getFirstName(),
                        teacherRequest.getMiddleName(),
                        teacherRequest.getLastName(),
                        teacherRequest.getNationality(),
                        teacherRequest.getDateOfBirth(),
                        teacherRequest.getPhoneNumber(),
                        teacherRequest.getAddress(),
                        teacherRequest.getPrivateEmail(),
                        teacherRequest.getSchoolEmail(),
                        teacherRequest.getTeacherType(),
                        teacherRequest.getGender(),
                        teacherRequest.getStatus()
                )
        );
        return ResponseEntity.created(URI.create(TeacherResource.PATH + "/" + createdTeacher.getId()))
                .body(TeacherMapper.INSTANCE.toDto(teacherService.addTeacher(createdTeacher)));
    }

    @DeleteMapping("/{teacherCode}")
    public ResponseEntity<Void> delete(@PathVariable String teacherCode) throws ResourceNotFoundException {
        Teacher teacher = teacherService.findTeacherByEmployeeCode(teacherCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + " not found")
                );
        teacherService.deleteTeacherByEmployeeCode(teacherCode);
        return ResponseEntity.noContent().build();
    }


}
