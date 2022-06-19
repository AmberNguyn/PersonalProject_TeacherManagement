package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.TeacherRequest;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.service.dto.TeacherSignedContractDto;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAll();

    Teacher create(Teacher teacher);
    Teacher create(TeacherRequest teacherRequest);

    Teacher update(TeacherRequest teacherRequest, Integer id);

    Optional<Teacher> findByEmployeeCode(String teacherCode);
    Optional<Teacher> findById(Integer id);

    void deleteById(Integer id);
    void deleteByEmployeeCode(String teacherCode);


    //find a list of teachers based on teacher type: vietnamese or expatriate
    List<Teacher> findTeacherByTeacherType(String teacherType);

    //find list of teachers who signed / haven't signed the contract
    List<TeacherSignedContractDto> findTeachersWhoSignedOrHaveNotSignedContract(String isSigned);
}
