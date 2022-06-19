package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.CertificationDetailRequest;
import com.example.TeacherManagement.entity.CertificationDetail;

import java.util.List;
import java.util.Optional;

public interface CertificationDetailService {
    List<CertificationDetail> getAll();

    CertificationDetail create(CertificationDetail certificationDetail);
    CertificationDetail create(CertificationDetailRequest certificationDetailRequest);

    List<CertificationDetail> findListByTeacherCode(String teacherCode);

    Optional<CertificationDetail> findById(Integer id);

    CertificationDetail update(CertificationDetailRequest certificationDetailRequest, Integer id);

    void deleteById(Integer id);

    // find a list of teachers who have a certain type of certificate
    List<CertificationDetail> findTeachersListWhoHaveCertificate(String certificationName);

    //find a list of teachers who can teach IELTS
    List<CertificationDetail> findIELTSTeacherListByCertificationNameAndScoreGreaterThan(String certificationName, double score);
}
