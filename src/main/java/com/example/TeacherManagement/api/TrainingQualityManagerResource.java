package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.TrainingQualityManagerService;
import com.example.TeacherManagement.service.dto.TrainingQualityManagerDto;
import com.example.TeacherManagement.service.mapper.TrainingQualityManagerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(TrainingQualityManagerResource.PATH)
public class TrainingQualityManagerResource {
    private TrainingQualityManagerService trainingQualityManagerService;

    public static final String PATH = "/api/trainingQualityManager";

    @GetMapping
    public ResponseEntity<List<TrainingQualityManagerDto>> getAll() {
        return ResponseEntity.ok(TrainingQualityManagerMapper.INSTANCE.toDtos(trainingQualityManagerService.getAll()));
    }
}
