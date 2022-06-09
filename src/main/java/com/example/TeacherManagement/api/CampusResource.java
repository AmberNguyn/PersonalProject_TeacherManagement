package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.CampusService;
import com.example.TeacherManagement.service.dto.CampusDto;
import com.example.TeacherManagement.service.mapper.CampusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(CampusResource.PATH)
public class CampusResource {
    @Autowired
    private CampusService campusService;

    public static final String PATH = "/api/campus";

    @GetMapping
    public ResponseEntity<List<CampusDto>> getAll() {
        return ResponseEntity.ok(CampusMapper.INSTANCE.toDtos(campusService.getAll()));
    }
}
