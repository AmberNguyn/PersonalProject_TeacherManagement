package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.CertificationDetailRequest;
import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.entity.CertificationDetail;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.CertificationDetailService;
import com.example.TeacherManagement.service.CertificationService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.CertificationDetailDto;
import com.example.TeacherManagement.service.mapper.CertificationDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<CertificationDetailDto>> getCertificationDetailListByTeacherCode(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
        List<CertificationDetail> certificationDetails = certificationDetailService.findCertificationDetailListByTeacherCode(teacherCode);
        if (certificationDetails.size() == 0) throw new ResourceNotFoundException("Certification detail of teacher code: " + teacherCode + " not found!");

        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDtos(certificationDetails));
    }

    @GetMapping("/findCertificationDetails/{id}")
    public ResponseEntity<CertificationDetailDto> getCertificationDetailById(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        CertificationDetail certificationDetail = certificationDetailService.findCertificationDetailById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certification detail id: " + id + " not found"));
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDto(certificationDetail));
    }

    @PostMapping
    public ResponseEntity<CertificationDetailDto> create(@RequestBody CertificationDetailRequest certificationDetailRequest) throws ResourceNotFoundException{
        Certification certificationRequest = certificationService.findCertificationById(certificationDetailRequest.getCertificationId())
                .orElseThrow(() -> new ResourceNotFoundException("Certification id: " + certificationDetailRequest.getCertificationId() + " not found"));

        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(certificationDetailRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher code: " + certificationDetailRequest.getTeacherCode() + " not found!"));

        CertificationDetail createdCertificationDetail = certificationDetailService.addCertificationDetail(
                new CertificationDetail(
                        null,
                        certificationDetailRequest.getScore(),
                        certificationDetailRequest.getIssuedDate(),
                        certificationDetailRequest.getExpiredDate(),
                        certificationDetailRequest.getDescription(),
                        certificationRequest,
                        teacherRequest
                )
        );

        return ResponseEntity.created(URI.create(CertificationDetailResource.PATH + "/" + createdCertificationDetail.getId()))
                .body(CertificationDetailMapper.INSTANCE.toDto(certificationDetailService.addCertificationDetail(createdCertificationDetail)));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        CertificationDetail certificationDetail = certificationDetailService.findCertificationDetailById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certification detail id: " + id + " not found!"));

        certificationDetailService.deleteCertificationDetailById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificationDetailDto> update(@PathVariable("id") Integer id,
                                                         @RequestBody CertificationDetailRequest certificationDetailRequest) throws ResourceNotFoundException {
        Certification certificationRequest = certificationService.findCertificationById(certificationDetailRequest.getCertificationId())
                .orElseThrow(() -> new ResourceNotFoundException("Certification id: " + certificationDetailRequest.getCertificationId() + " not found"));

        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(certificationDetailRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher code: " + certificationDetailRequest.getTeacherCode() + " not found!"));


        CertificationDetail editedCertificationDetail = certificationDetailService.findCertificationDetailById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certification detail id: " + id + " not found"));

        editedCertificationDetail.setScore(certificationDetailRequest.getScore());
        editedCertificationDetail.setIssuedDate(certificationDetailRequest.getIssuedDate());
        editedCertificationDetail.setExpiredDate(certificationDetailRequest.getExpiredDate());
        editedCertificationDetail.setDescription(certificationDetailRequest.getDescription());
        editedCertificationDetail.setCertification(certificationRequest);
        editedCertificationDetail.setTeacher(teacherRequest);

        CertificationDetail updatedCertificationDetail = certificationDetailService.addCertificationDetail(editedCertificationDetail);
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDto(updatedCertificationDetail));
    }


    // ------------ TEST POSTMAN------------
    @GetMapping("/certificate/")
    public ResponseEntity<List<CertificationDetailDto>> findTeachersWhoHaveCertificate(@RequestParam String certificate) throws ResourceNotFoundException {
        List<CertificationDetail> teachersWithCertification = certificationDetailService.findTeachersListWhoHaveCertificate(certificate);
        if (teachersWithCertification.size() == 0 ) throw new ResourceNotFoundException("There is no teacher with " + certificate);
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDtos(teachersWithCertification));
    }

    // ------------ TEST POSTMAN------------
    @GetMapping("/certification/score")
    public ResponseEntity<List<CertificationDetailDto>> findTeachersWhoCanTeachIELTS(@RequestParam String certificate,
                                                                                     @RequestParam double score) throws ResourceNotFoundException {
        List<CertificationDetail> teachersWithIELTS = certificationDetailService.findIELTSTeacherListByCertificationNameAndScoreGreaterThan("IELTS", 6.0);
        if (teachersWithIELTS.size() == 0) throw new ResourceNotFoundException("There is no teacher with " + certificate + " greater than " + score);

        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDtos(teachersWithIELTS));

    }
}
