package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.entity.TeacherType;
import com.example.TeacherManagement.repository.TeacherRepository;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.TeacherSignedContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> findTeacherByEmployeeCode(String teacherCode) {
        return Optional.of(teacherRepository.findTeacherByEmployeeCode(teacherCode));
    }

    @Override
    public void deleteTeacherByEmployeeCode(String teacherCode) {
        teacherRepository.delete(teacherRepository.findTeacherByEmployeeCode(teacherCode));
    }

    //find a list of teachers based on teacher type: vietnamese or expatriate
    @Override
    public List<Teacher> findTeacherByTeacherType(String teacherType) {
        return teacherRepository.findTeacherByTeacherType(TeacherType.valueOf(teacherType));
    }

    //find list of teachers who signed / haven't signed the contract
    @Override
    public List<TeacherSignedContractDto> findTeachersWhoSignedOrHaveNotSignedContract(String isSigned) {
        return teacherRepository.findTeacherListWhoSignedContract(Boolean.parseBoolean(isSigned));
    }
}
