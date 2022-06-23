package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.entity.TeacherType;
import com.example.TeacherManagement.service.dto.TeacherSignedContractDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findTeacherByEmployeeCode(String teacherCode);

    //find a list of teachers based on teacher type: vietnamese or expatriate
    List<Teacher> findTeacherByTeacherType(TeacherType teacherType);

    //find a list of teacher who signed / haven't signed the contract
    @Query(value = "SELECT new com.example.TeacherManagement.service.dto.TeacherSignedContractDto(tc.employeeCode, tc.firstName, tc.lastName, ct.isSigned) " +
            "FROM Teacher tc JOIN Contract ct ON tc.id = ct.teacher.id " +
            "WHERE ct.isSigned = ?1")
    List<TeacherSignedContractDto> findTeacherListWhoSignedContract(Boolean isSigned);



}
