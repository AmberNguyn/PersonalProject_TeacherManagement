package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.CertificationDetailRequest;
import com.example.TeacherManagement.entity.CertificationDetail;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.service.CertificationDetailService;
import com.example.TeacherManagement.service.CertificationService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.CertificationDetailDto;
import com.example.TeacherManagement.service.mapper.CertificationDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(CertificationDetailResource.PATH)
public class CertificationDetailResource {
    public static final String PATH = "/api/certificationDetails";

    @Autowired
    private CertificationDetailService certificationDetailService;
    @Autowired
    private CertificationService certificationService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<CertificationDetailDto>> getAll()
    {
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDtos(certificationDetailService.getAll()));
    }

    @GetMapping("/find")
    public ResponseEntity<List<CertificationDetailDto>> getCertificationDetailListByTeacherCode(@RequestParam("teacherCode") String teacherCode) {
        List<CertificationDetail> certificationDetails = certificationDetailService.findListByTeacherCode(teacherCode);

        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDtos(certificationDetails));
    }

    // --------- CHECK POSTMAN ---------
    @GetMapping("/find/{id}")
    public ResponseEntity<CertificationDetailDto> getCertificationDetailById(@PathVariable("id") Integer id){
        log.info("Searched id: {}", id);
        CertificationDetail certificationDetail = certificationDetailService.findById(id)
                .orElseThrow(BusinessLogicException::CertificateDetailIdNotFound);
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDto(certificationDetail));
    }

    // --------- CHANGE POSTMAN ---------
    @PostMapping
    public ResponseEntity<CertificationDetailDto> create(@RequestBody @Valid CertificationDetailRequest certificationDetailRequest) {
        CertificationDetail createdCertificationDetail = certificationDetailService.create(certificationDetailRequest);

        return ResponseEntity.created(URI.create(CertificationDetailResource.PATH + "/" + createdCertificationDetail.getId()))
                .body(CertificationDetailMapper.INSTANCE.toDto(certificationDetailService.create(createdCertificationDetail)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        log.info("Searched id: {}", id);

        CertificationDetail certificationDetail = certificationDetailService.findById(id)
                .orElseThrow(BusinessLogicException::CertificateDetailIdNotFound);

        certificationDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // --------- CHANGE POSTMAN PATH ------
    @PutMapping("/{id}")
    public ResponseEntity<CertificationDetailDto> update(@PathVariable("id") Integer id,
                                                         @RequestBody @Valid CertificationDetailRequest certificationDetailRequest) {

        CertificationDetail updatedCertificationDetail = certificationDetailService.update(certificationDetailRequest, id);
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDto(updatedCertificationDetail));
    }


    @GetMapping("/certificate")
    public ResponseEntity<List<CertificationDetailDto>> findTeachersWhoHaveCertificate(@RequestParam String certificate) {
        List<CertificationDetail> teachersWithCertification = certificationDetailService.findTeachersListWhoHaveCertificate(certificate);
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDtos(teachersWithCertification));
    }


    @GetMapping("/certificate-and-score")
    public ResponseEntity<List<CertificationDetailDto>> findTeachersWhoCanTeachIELTS(@RequestParam("certificate") String certificate,
                                                                                     @RequestParam("score") double score) {
        List<CertificationDetail> teachersWithIELTS = certificationDetailService.findIELTSTeacherListByCertificationNameAndScoreGreaterThan(certificate, score);
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDtos(teachersWithIELTS));

    }
}
