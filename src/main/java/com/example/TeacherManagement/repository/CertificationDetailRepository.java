package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.CertificationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationDetailRepository extends JpaRepository<CertificationDetail, Integer> {
    CertificationDetail findCertificationDetailByTeacherEmployeeCodeContaining(String teacherCode);
}
