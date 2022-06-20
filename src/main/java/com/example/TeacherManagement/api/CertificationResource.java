package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.CertificationRequest;
import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.exception.BusinessLogicException;
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
    public ResponseEntity<CertificationDto> getCertificateById(@PathVariable("id") Integer id){
        Certification certification = certificationService.findById(id)
                .orElseThrow(BusinessLogicException::CertificateIdNotFound);
        return ResponseEntity.ok(CertificationMapper.INSTANCE.toDto(certification));
    }

    // -------- CHECK POSTMAN -----------
    @PostMapping
    public ResponseEntity<CertificationDto> create(@RequestBody CertificationRequest certificationRequest) {
        Certification createdCertification = certificationService.create(certificationRequest);

          return ResponseEntity.created(URI.create(CertificationResource.PATH + "/" + createdCertification.getId()))
                  .body(CertificationMapper.INSTANCE.toDto(certificationService.create(createdCertification)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Certification certification = certificationService.findById(id)
                .orElseThrow(BusinessLogicException::CertificateIdNotFound);
        certificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // -------- CHECK POSTMAN ----------
    @PutMapping("/{id}")
    public ResponseEntity<CertificationDto> update(@PathVariable("id") Integer id,
                                                   @RequestBody CertificationRequest certificationRequest) {

        Certification updatedCertification = certificationService.update(certificationRequest, id);
        return ResponseEntity.ok(CertificationMapper.INSTANCE.toDto(updatedCertification));


    }

}
