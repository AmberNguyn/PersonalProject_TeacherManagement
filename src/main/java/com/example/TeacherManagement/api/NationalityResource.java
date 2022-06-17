package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.NationalityRequest;
import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.NationalityService;
import com.example.TeacherManagement.service.dto.NationalityDto;
import com.example.TeacherManagement.service.mapper.NationalityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(NationalityResource.PATH)
public class NationalityResource {
    public static final String PATH = "/api/nationalities";

    @Autowired
    private NationalityService nationalityService;

    @GetMapping
    public ResponseEntity<List<NationalityDto>> getAll() {
        return ResponseEntity.ok(NationalityMapper.INSTANCE.toDtos(nationalityService.getAll()));
    }

    @GetMapping("/")
    public ResponseEntity<NationalityDto> getNationalityByCountryCode(@RequestParam("countryCode") String countryCode) throws ResourceNotFoundException {
        Nationality nationality = nationalityService.findNationalityByCountryCode(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException(countryCode + " not found!"));
        return ResponseEntity.ok(NationalityMapper.INSTANCE.toDto(nationality));
    }


    @GetMapping("/{id}")
    public ResponseEntity<NationalityDto> getNationalityById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Nationality nationality = nationalityService.findNationalityById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found!"));
        return ResponseEntity.ok(NationalityMapper.INSTANCE.toDto(nationality));
    }

    @PostMapping
    public ResponseEntity<NationalityDto> create(@RequestBody NationalityRequest nationalityRequest) {
        Nationality createdNationality = nationalityService.addNationality(
                new Nationality(
                        null,
                        nationalityRequest.getCountry(),
                        nationalityRequest.getCountryCode(),
                        nationalityRequest.getNationality()
                )
        );

        return ResponseEntity.created(URI.create(NationalityResource.PATH + "/" + createdNationality.getId()))
                .body(NationalityMapper.INSTANCE.toDto(nationalityService.addNationality(createdNationality)));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("countryCode") String countryCode) throws ResourceNotFoundException{
        Nationality nationality = nationalityService.findNationalityByCountryCode(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException(countryCode + " not found!"));

        nationalityService.findNationalityByCountryCode(countryCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<NationalityDto> update(@RequestParam("countryCode") String countryCode,
                                                 @RequestBody NationalityRequest nationalityRequest) throws ResourceNotFoundException {
        Nationality editedNationality = nationalityService.findNationalityByCountryCode(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException(countryCode + " not found"));

        editedNationality.setCountryCode(nationalityRequest.getCountryCode());
        editedNationality.setCountry(nationalityRequest.getCountry());
        editedNationality.setNationality(nationalityRequest.getNationality());

        Nationality updatedNationality = nationalityService.addNationality(editedNationality);

        return ResponseEntity.ok(NationalityMapper.INSTANCE.toDto(updatedNationality));
    }


}
