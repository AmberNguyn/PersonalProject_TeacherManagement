package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.service.dto.TeachersAndExpiredContractsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findContractByTeacherEmployeeCodeIgnoreCase(String employeeCode);

    Contract findContractByContractId(String contractId);

    //find teachers whose contracts are about to expire

    @Query(value = "SELECT new com.example.TeacherManagement.service.dto.TeachersAndExpiredContractsDto(tc.employeeCode, tc.firstName, ct.endDate, ct.contractId) " +
            "FROM Teacher tc , Contract ct WHERE tc.id = ct.teacher.id " +
            "AND (EXTRACT (YEAR FROM ct.endDate) = ?1 AND ct.isSigned = TRUE)")
    List<TeachersAndExpiredContractsDto> findTeachersAndTheirExpiredContractInYear(Integer year);
}
