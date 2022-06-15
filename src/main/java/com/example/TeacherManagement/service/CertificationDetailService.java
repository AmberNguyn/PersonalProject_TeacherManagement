package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.CertificationDetail;

import java.util.List;
import java.util.Optional;

public interface CertificationDetailService {
    List<CertificationDetail> getAll();

    CertificationDetail addCertificationDetail(CertificationDetail certificationDetail);

    List<CertificationDetail> findCertificationDetailListByTeacherCode(String teacherCode);

    Optional<CertificationDetail> findCertificationDetailById(Integer id);

    void deleteCertificationDetailById(Integer id);

    // find a list of teachers who have a certain type of certificate
    List<CertificationDetail> findTeachersListWhoHaveCertificate(String certificationName);
}
