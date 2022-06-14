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
    public Optional<CertificationDetail> findCertificationDetailByTeacherCode(String teacherCode) {
        return Optional.of(certificationDetailRepository.findCertificationDetailByTeacherEmployeeCodeContaining(teacherCode));
    }

    @Override
    public void deleteCertificationDetailByTeacherCode(String teacherCode) {
        certificationDetailRepository.delete(certificationDetailRepository.findCertificationDetailByTeacherEmployeeCodeContaining(teacherCode));
    }
}
