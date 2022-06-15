package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.CertificationDetail;
import com.example.TeacherManagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationDetailRepository extends JpaRepository<CertificationDetail, Integer> {
    List<CertificationDetail> findCertificationDetailListByTeacherEmployeeCodeContaining(String teacherCode);

    // find a list of teachers who have a certain type of certificate
    List<CertificationDetail> findByCertificationName(String certificationName);

    //find a list of teachers who can teach IELTS class (= have IELTS > 7.0)
    List<CertificationDetail> findIELTSTeacherListByCertificationNameAndScoreGreaterThan(String certificationName, double score);
}
