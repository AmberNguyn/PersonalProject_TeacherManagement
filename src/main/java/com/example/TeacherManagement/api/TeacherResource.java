package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.TeacherRequest;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.MyException;
import com.example.TeacherManagement.service.NationalityService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.TeacherDto;
import com.example.TeacherManagement.service.dto.TeacherSignedContractDto;
import com.example.TeacherManagement.service.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(TeacherResource.PATH)
public class TeacherResource {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private NationalityService nationalityService;


    public static final String PATH = "/api/teachers";

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDtos(teacherService.getAll()));
    }

    // ------ CHECK POSTMAN ------
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching id: {}", id);
        Teacher teacher = teacherService.findById(id)
                .orElseThrow(MyException::TeacherIdNotFound);
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDto(teacher));
    }


    @GetMapping("/find")
    public ResponseEntity<TeacherDto> getTeacherByTeacherCode(@RequestParam("teacherCode") String teacherCode) {
        log.info("Searching teacher code: {}", teacherCode);
        Teacher teacher = teacherService.findByEmployeeCode(teacherCode)
                .orElseThrow(MyException::TeacherCodeNotFound);
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDto(teacher));
    }


    // ---- check postman ----
    @PostMapping
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherRequest teacherRequest){
        Teacher createdTeacher = teacherService.create(teacherRequest);

        return ResponseEntity.created(URI.create(TeacherResource.PATH + "/" + createdTeacher.getId()))
                .body(TeacherMapper.INSTANCE.toDto(teacherService.create(createdTeacher)));
    }


    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode) {
        log.info("Searching teacher code: {}", teacherCode);
        Teacher teacher = teacherService.findByEmployeeCode(teacherCode)
                .orElseThrow(MyException::TeacherCodeNotFound);
        teacherService.deleteByEmployeeCode(teacherCode);
        return ResponseEntity.noContent().build();
    }


    // ---- check postman ---
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable("id") Integer id,
                                             @RequestBody TeacherRequest teacherRequest) {
        Teacher updatedTeacher = teacherService.update(teacherRequest, id);
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDto(updatedTeacher));

    }


    @GetMapping("/find-by-teacher-type")
    public ResponseEntity<List<TeacherDto>> findTeacherByTeacherType(@RequestParam("teacherType") String teacherType){
        List<Teacher> teachers = teacherService.findTeacherByTeacherType(teacherType);
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toDtos(teachers));
    }


    @GetMapping("/find-who-signed")
    public ResponseEntity<List<TeacherSignedContractDto>> findTeachersWhoSignedOrNotSignedContract(@RequestParam("isSigned") String isSigned){
        List<TeacherSignedContractDto> teachersSignedContract = teacherService.findTeachersWhoSignedOrHaveNotSignedContract(isSigned);
        return ResponseEntity.ok(teachersSignedContract);
    }


}
