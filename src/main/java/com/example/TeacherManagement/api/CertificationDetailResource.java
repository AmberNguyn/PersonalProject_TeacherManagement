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
    public ResponseEntity<CertificationDetailDto> getCertificationDetailByTeacherCode(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
        CertificationDetail certificationDetail = certificationDetailService.findCertificationDetailByTeacherCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher code: " + teacherCode + " not found. Certification not found!"));

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


    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException{
        CertificationDetail certificationDetail = certificationDetailService.findCertificationDetailByTeacherCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher code: " + teacherCode + " for certification detail not found!"));

        certificationDetailService.deleteCertificationDetailByTeacherCode(teacherCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<CertificationDetailDto> update(@RequestParam("teacherCode") String teacherCode,
                                                         @RequestBody CertificationDetailRequest certificationDetailRequest) throws ResourceNotFoundException {
        Certification certificationRequest = certificationService.findCertificationById(certificationDetailRequest.getCertificationId())
                .orElseThrow(() -> new ResourceNotFoundException("Certification id: " + certificationDetailRequest.getCertificationId() + " not found"));

        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(certificationDetailRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher code: " + certificationDetailRequest.getTeacherCode() + " not found!"));


        CertificationDetail editedCertificationDetail = certificationDetailService.findCertificationDetailByTeacherCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher code: " + teacherCode + " for certification detail not found!"));

        editedCertificationDetail.setScore(certificationDetailRequest.getScore());
        editedCertificationDetail.setIssuedDate(certificationDetailRequest.getIssuedDate());
        editedCertificationDetail.setExpiredDate(certificationDetailRequest.getExpiredDate());
        editedCertificationDetail.setDescription(certificationDetailRequest.getDescription());

        CertificationDetail updatedCertificationDetail = certificationDetailService.addCertificationDetail(editedCertificationDetail);
        return ResponseEntity.ok(CertificationDetailMapper.INSTANCE.toDto(updatedCertificationDetail));
    }

}
