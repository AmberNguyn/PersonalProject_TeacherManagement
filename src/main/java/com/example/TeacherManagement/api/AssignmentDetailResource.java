package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.dto.AssignmentDetailDto;
import com.example.TeacherManagement.service.mapper.AssignmentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(AssignmentDetailResource.PATH)

public class AssignmentDetailResource {
    @Autowired
    private AssignmentDetailService assignmentDetailService;

    public static final String PATH = "/api/assignmentDetail";

    @GetMapping
    public ResponseEntity<List<AssignmentDetailDto>> getAll() {
        return ResponseEntity.ok(AssignmentDetailMapper.INSTANCE.toDtos(assignmentDetailService.getAll()));
    }
}
