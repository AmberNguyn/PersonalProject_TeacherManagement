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


    @GetMapping("/find")
    public ResponseEntity<TeacherDto> getTeacherByTeacherCode(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
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

    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
        Teacher teacher = teacherService.findTeacherByEmployeeCode(teacherCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + " not found")
                );
        teacherService.deleteTeacherByEmployeeCode(teacherCode);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/")
    public ResponseEntity<TeacherDto> update(@RequestParam("teacherCode") String teacherCode,
                                             @RequestBody TeacherRequest teacherRequest) throws ResourceNotFoundException{
        Teacher editedTeacher = teacherService.findTeacherByEmployeeCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + " not found!"));

        editedTeacher.setEmployeeCode(teacherRequest.getEmployeeCode());
        editedTeacher.setFirstName(teacherRequest.getFirstName());
        editedTeacher.setMiddleName(teacherRequest.getMiddleName());
        editedTeacher.setLastName(teacherRequest.getLastName());
        editedTeacher.setNationality(teacherRequest.getNationality());
        editedTeacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        editedTeacher.setPhoneNumber(teacherRequest.getPhoneNumber());
        editedTeacher.setAddress(teacherRequest.getAddress());
        editedTeacher.setPrivateEmail(teacherRequest.getPrivateEmail());
        editedTeacher.setSchoolEmail(teacherRequest.getSchoolEmail());
        editedTeacher.setTeacherType(teacherRequest.getTeacherType());
        editedTeacher.setGender(teacherRequest.getGender());
        editedTeacher.setStatus(teacherRequest.getStatus());

        Teacher updatedTeacher = teacherService.addTeacher(editedTeacher);
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDto(updatedTeacher));

    }


}
