package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.CertificationDetail;

import java.util.List;
import java.util.Optional;

public interface CertificationDetailService {
    List<CertificationDetail> getAll();

    CertificationDetail addCertificationDetail(CertificationDetail certificationDetail);

    Optional<CertificationDetail> findCertificationDetailByTeacherCode(String teacherCode);

    void deleteCertificationDetailByTeacherCode(String teacherCode);
}
