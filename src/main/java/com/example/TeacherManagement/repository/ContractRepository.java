package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Contract findContractByTeacherEmployeeCodeIgnoreCase(String employeeCode);
}
