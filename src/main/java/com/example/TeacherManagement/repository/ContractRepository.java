package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findContractByTeacherEmployeeCodeIgnoreCase(String employeeCode);

    Contract findContractByContractId(String contractId);

}
