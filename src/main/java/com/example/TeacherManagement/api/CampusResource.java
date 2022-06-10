package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.Campus;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.CampusService;
import com.example.TeacherManagement.service.dto.CampusDto;
import com.example.TeacherManagement.service.mapper.CampusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{campusName}")
    public ResponseEntity<CampusDto> getCampusByName(@PathVariable String campusName) throws ResourceNotFoundException {
        Campus campus = campusService.findCampusByName(campusName)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Campus " + campusName + " not found!")
                );
        return ResponseEntity.ok(CampusMapper.INSTANCE.toDto(campus));
    }

    @PostMapping
    public ResponseEntity<CampusDto> create(@RequestBody Campus campus) {
        Campus createdCampus = campusService.addCampus(campus);
        return ResponseEntity.created(URI.create(CampusResource.PATH + "/" + createdCampus.getName()))
                .body(CampusMapper.INSTANCE.toDto(createdCampus));
    }

    @DeleteMapping("/{campusName}")
    public ResponseEntity<Void> delete(@PathVariable String campusName) throws ResourceNotFoundException {
        Campus campus = campusService.findCampusByName(campusName)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Campus " + campusName + " not found")
                );
        campusService.deleteCampusByName(campusName);
        return ResponseEntity.noContent().build();
    }
}
