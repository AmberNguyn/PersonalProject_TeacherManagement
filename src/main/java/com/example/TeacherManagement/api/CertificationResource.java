package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.CertificationRequest;
import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.CertificationService;
import com.example.TeacherManagement.service.dto.CertificationDto;
import com.example.TeacherManagement.service.mapper.CertificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CertificationResource.PATH)
public class CertificationResource {
    public static final String PATH = "/api/certifications";

    @Autowired
    private CertificationService certificationService;

    @GetMapping
    public ResponseEntity<List<CertificationDto>> getAll() {
        return ResponseEntity.ok(CertificationMapper.INSTANCE.toDtos(certificationService.getAll()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CertificationDto> getCertificateById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Certification certification = certificationService.findCertificationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certification id: " + id + " not found!"));
        return ResponseEntity.ok(CertificationMapper.INSTANCE.toDto(certification));
    }

    @PostMapping
    public ResponseEntity<CertificationDto> create(@RequestBody CertificationRequest certificationRequest) {
        Certification createdCertification = certificationService.addCertification(
                new Certification(
                        null,
                        certificationRequest.getName()
                )
        );
          return ResponseEntity.created(URI.create(CertificationResource.PATH + "/" + createdCertification.getId()))
                  .body(CertificationMapper.INSTANCE.toDto(certificationService.addCertification(createdCertification)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Certification certification = certificationService.findCertificationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certification id: " + id + " not found!"));
        certificationService.deleteCertificationById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificationDto> update(@PathVariable("id") Integer id,
                                                   @RequestBody CertificationRequest certificationRequest) throws ResourceNotFoundException {

        Certification editedCertification = certificationService.findCertificationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certification id: " + id + " not found!"));

        editedCertification.setName(certificationRequest.getName());

        Certification updatedCertification = certificationService.addCertification(editedCertification);
        return ResponseEntity.ok(CertificationMapper.INSTANCE.toDto(updatedCertification));


    }

}
