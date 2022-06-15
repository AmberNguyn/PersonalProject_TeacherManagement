package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.CertificationDetail;
import com.example.TeacherManagement.repository.CertificationDetailRepository;
import com.example.TeacherManagement.service.CertificationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificationDetailServiceImpl implements CertificationDetailService {
    @Autowired
    private final CertificationDetailRepository certificationDetailRepository;

    @Override
    public List<CertificationDetail> getAll() {
        return certificationDetailRepository.findAll();
    }

    @Override
    public CertificationDetail addCertificationDetail(CertificationDetail certificationDetail) {
        return certificationDetailRepository.save(certificationDetail);
    }

    @Override
    public List<CertificationDetail> findCertificationDetailListByTeacherCode(String teacherCode) {
        return certificationDetailRepository.findCertificationDetailListByTeacherEmployeeCodeContaining(teacherCode);
    }

    @Override
    public Optional<CertificationDetail> findCertificationDetailById(Integer id) {
        return certificationDetailRepository.findById(id);
    }

    @Override
    public void deleteCertificationDetailById(Integer id) {
        certificationDetailRepository.deleteById(id);
    }

    // find a list of teachers who have a certain type of certificate
    @Override
    public List<CertificationDetail> findTeachersListWhoHaveCertificate(String certificationName) {
        return certificationDetailRepository.findByCertificationName(certificationName);
    }
}
