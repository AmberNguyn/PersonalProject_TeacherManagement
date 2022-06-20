package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.NationalityRequest;
import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.service.NationalityService;
import com.example.TeacherManagement.service.dto.NationalityDto;
import com.example.TeacherManagement.service.mapper.NationalityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
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

    @GetMapping("/find-by-country-code")
    public ResponseEntity<NationalityDto> getNationalityByCountryCode(@RequestParam("countryCode") String countryCode) {
        log.info("Searching country code: {}", countryCode);
        Nationality nationality = nationalityService.findByCountryCode(countryCode)
                .orElseThrow(BusinessLogicException::CountryCodeNotFound);
        return ResponseEntity.ok(NationalityMapper.INSTANCE.toDto(nationality));
    }


    @GetMapping("/{id}")
    public ResponseEntity<NationalityDto> getNationalityById(@PathVariable("id") Integer id) {
        log.info("Searching nationality id: {}", id);
        Nationality nationality = nationalityService.findById(id)
                .orElseThrow(BusinessLogicException::NationalityIdNotFound);
        return ResponseEntity.ok(NationalityMapper.INSTANCE.toDto(nationality));
    }

    // ------ CHECK POSTMAN -----
    @PostMapping
    public ResponseEntity<NationalityDto> create(@RequestBody NationalityRequest nationalityRequest) {
        Nationality createdNationality = nationalityService.create(nationalityRequest);
        return ResponseEntity.created(URI.create(NationalityResource.PATH + "/" + createdNationality.getId()))
                .body(NationalityMapper.INSTANCE.toDto(nationalityService.create(createdNationality)));
    }

    // ----- check POSTMAN
    @DeleteMapping
    public ResponseEntity<Void> deleteByCountryCode(@RequestParam("countryCode") String countryCode) {
        log.info("Searching country code: {}", countryCode);
        Nationality nationality = nationalityService.findByCountryCode(countryCode)
                .orElseThrow(BusinessLogicException::CountryCodeNotFound);

        nationalityService.deleteByCountryCode(countryCode);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        log.info("Searching nationality id: {}", id);
        Nationality nationality = nationalityService.findById(id)
                .orElseThrow(BusinessLogicException::NationalityIdNotFound);
        nationalityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<NationalityDto> update(@PathVariable("id") Integer id,
                                                 @RequestBody NationalityRequest nationalityRequest) {

        Nationality updatedNationality = nationalityService.update(nationalityRequest, id);

        return ResponseEntity.ok(NationalityMapper.INSTANCE.toDto(updatedNationality));
    }


}
