package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.CampusRequest;
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
    @Autowired
    CampusMapper campusMapper;


    public static final String PATH = "/api/campuses";

    @GetMapping
    public ResponseEntity<List<CampusDto>> getAll() {
        return ResponseEntity.ok(campusMapper.toDtos(campusService.getAll()));
    }

    @GetMapping("/find")
    public ResponseEntity<CampusDto> getCampusByName(@RequestParam("campusCode") String campusCode) throws ResourceNotFoundException {
        Campus campus = campusService.findCampusByCampusCode(campusCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Campus " + campusCode + " not found!")
                );
        return ResponseEntity.ok(CampusMapper.INSTANCE.toDto(campus));
    }


    @PostMapping
    public ResponseEntity<CampusDto> create(@RequestBody Campus campusRequest) {
        Campus createdCampus = campusService.addCampus(
                new Campus(
                        null,
                        campusRequest.getCampusCode(),
                        campusRequest.getName(),
                        campusRequest.getAddress(),
                        campusRequest.getNumberOfRooms()
                )
        );

        return ResponseEntity.created(URI.create(CampusResource.PATH + "/" + createdCampus.getId()))
                .body(CampusMapper.INSTANCE.toDto(campusService.addCampus(createdCampus)));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("campusCode") String campusCode) throws ResourceNotFoundException {
        Campus campus = campusService.findCampusByCampusCode(campusCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Campus " + campusCode + " not found")
                );
        campusService.deleteCampusByCampusCode(campusCode);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/")
    public ResponseEntity<CampusDto> update(@RequestParam("campusCode") String campusCode,
                                            @RequestBody CampusRequest campusRequest) throws ResourceNotFoundException{

        Campus editedCampus = campusService.findCampusByCampusCode(campusRequest.getCampusCode())
                .orElseThrow(() -> new  ResourceNotFoundException(campusCode + "not found"));

        editedCampus.setCampusCode(campusRequest.getCampusCode());
        editedCampus.setAddress(campusRequest.getAddress());
        editedCampus.setName(campusRequest.getName());
        editedCampus.setNumberOfRooms(campusRequest.getNumberOfRooms());

        Campus updatedCampus = campusService.addCampus(editedCampus);
        return ResponseEntity.ok(CampusMapper.INSTANCE.toDto(updatedCampus));
    }
}
