package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.CertificationDetailRequest;
import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.entity.CertificationDetail;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.repository.CertificationDetailRepository;
import com.example.TeacherManagement.service.CertificationDetailService;
import com.example.TeacherManagement.service.CertificationService;
import com.example.TeacherManagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CertificationDetailServiceImpl implements CertificationDetailService {
    @Autowired
    private final CertificationDetailRepository certificationDetailRepository;
    @Autowired
    private final CertificationService certificationService;
    @Autowired
    private final TeacherService teacherService;


    @Override
    public List<CertificationDetail> getAll() {
        return certificationDetailRepository.findAll();
    }

    @Override
    public CertificationDetail create(CertificationDetail certificationDetail) {
        return certificationDetailRepository.save(certificationDetail);
    }

    @Override
    public CertificationDetail create(CertificationDetailRequest certificationDetailRequest) {
        Certification certificationRequest = certificationService.findById(certificationDetailRequest.getCertificationId())
                .orElseThrow(BusinessLogicException::CertificateDetailIdNotFound);
        log.info("Searched certification detail id: {}", certificationDetailRequest.getCertificationId());

        Teacher teacherRequest = teacherService.findByEmployeeCode(certificationDetailRequest.getTeacherCode())
                .orElseThrow(BusinessLogicException::TeacherCodeNotFound);
        log.info("Searched certification detail request: {}", certificationDetailRequest.getTeacherCode());

        CertificationDetail createdCertificationDetail = new CertificationDetail();
        createdCertificationDetail.setTeacher(teacherRequest);
        createdCertificationDetail.setCertification(certificationRequest);
        createdCertificationDetail.setScore(certificationDetailRequest.getScore());
        createdCertificationDetail.setIssuedDate(certificationDetailRequest.getIssuedDate());
        createdCertificationDetail.setExpiredDate(certificationDetailRequest.getExpiredDate());
        createdCertificationDetail.setDescription(certificationDetailRequest.getDescription());

        return certificationDetailRepository.save(createdCertificationDetail);
    }

    @Override
    public List<CertificationDetail> findListByTeacherCode(String teacherCode) {
        return certificationDetailRepository.findCertificationDetailListByTeacherEmployeeCodeContaining(teacherCode);
    }

    @Override
    public Optional<CertificationDetail> findById(Integer id) {
        return certificationDetailRepository.findById(id);
    }

    @Override
    public CertificationDetail update(CertificationDetailRequest certificationDetailRequest, Integer id) {
        Certification certificationRequest = certificationService.findById(certificationDetailRequest.getCertificationId())
                .orElseThrow(BusinessLogicException::CertificateIdNotFound);
        log.info("Searched certification detail id: {}", certificationDetailRequest.getCertificationId() );

        Teacher teacherRequest = teacherService.findByEmployeeCode(certificationDetailRequest.getTeacherCode())
                .orElseThrow(BusinessLogicException::TeacherCodeNotFound);
        log.info("Searched teacher code: {}" + certificationDetailRequest.getTeacherCode());

        CertificationDetail editedCertificationDetail = certificationDetailRepository.findById(id)
                .orElseThrow(BusinessLogicException::CertificateDetailIdNotFound);
        log.info("Searched certificate id: {}", certificationDetailRepository.findById(id));

        editedCertificationDetail.setScore(certificationDetailRequest.getScore());
        editedCertificationDetail.setIssuedDate(certificationDetailRequest.getIssuedDate());
        editedCertificationDetail.setExpiredDate(certificationDetailRequest.getExpiredDate());
        editedCertificationDetail.setDescription(certificationDetailRequest.getDescription());
        editedCertificationDetail.setCertification(certificationRequest);
        editedCertificationDetail.setTeacher(teacherRequest);

        return certificationDetailRepository.save(editedCertificationDetail);
    }

    @Override
    public void deleteById(Integer id) {
        certificationDetailRepository.deleteById(id);
    }

    // find a list of teachers who have a certain type of certificate
    @Override
    public List<CertificationDetail> findTeachersListWhoHaveCertificate(String certificationName) {
        return certificationDetailRepository.findByCertificationName(certificationName);
    }

    //find a list of teachers who can teach IELTS
    @Override
    public List<CertificationDetail> findIELTSTeacherListByCertificationNameAndScoreGreaterThan(String certificationName, double score) {
        return certificationDetailRepository.findIELTSTeacherListByCertificationNameAndScoreGreaterThan(certificationName, score);
    }
}
