package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.TrainingQualityManager;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.TrainingQualityManagerService;
import com.example.TeacherManagement.service.dto.TrainingQualityManagerDto;
import com.example.TeacherManagement.service.mapper.TrainingQualityManagerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{employeeCode}")
    public ResponseEntity<TrainingQualityManagerDto> getTrainingQualityManagerBy(@PathVariable String employeeCode) throws ResourceNotFoundException {
        TrainingQualityManager trainingQualityManager = trainingQualityManagerService.findTrainingQualityManagerByEmployeeCode(employeeCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(employeeCode + " not found")
                );
        return ResponseEntity.ok(TrainingQualityManagerMapper.INSTANCE.toDto(trainingQualityManager));
    }

    @PostMapping
    public ResponseEntity<TrainingQualityManagerDto> create(@RequestBody TrainingQualityManager trainingQualityManager) {
        TrainingQualityManager createdTrainingQualityManager = trainingQualityManagerService.addTrainingQualityManager(trainingQualityManager);
        return ResponseEntity.created(URI.create(TrainingQualityManagerResource.PATH + "/" + createdTrainingQualityManager.getEmployeeCode()))
                .body(TrainingQualityManagerMapper.INSTANCE.toDto(createdTrainingQualityManager));
    }

    @DeleteMapping("/{employeeCode}")
    public ResponseEntity<Void> delete(@PathVariable String employeeCode) throws ResourceNotFoundException {
        TrainingQualityManager trainingQualityManager = trainingQualityManagerService.findTrainingQualityManagerByEmployeeCode(employeeCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(employeeCode + " not found")
                );
        trainingQualityManagerService.deleteTrainingQualityManagerByEmployeeCode(employeeCode);
        return ResponseEntity.noContent().build();
    }
}
