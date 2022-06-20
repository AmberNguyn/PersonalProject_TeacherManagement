package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.TeacherRequest;
import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.entity.TeacherType;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.repository.TeacherRepository;
import com.example.TeacherManagement.service.NationalityService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.TeacherSignedContractDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;
    @Autowired
    private NationalityService nationalityService;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher create(TeacherRequest teacherRequest){
        Nationality nationalityRequest = nationalityService.findById(teacherRequest.getNationalityId())
                .orElseThrow(BusinessLogicException::NationalityIdNotFound);
        log.info("Searched nationality by id: {}",teacherRequest.getNationalityId());

//        if(teacherRepository.findTeacherByEmployeeCode(teacherRequest.getEmployeeCode()).getEmployeeCode().equals(teacherRequest.getEmployeeCode())) {
//            throw new MyException::DuplicateId;
//        }

        Teacher createdTeacher = new Teacher();
        createdTeacher.setEmployeeCode(teacherRequest.getEmployeeCode());
        createdTeacher.setFirstName(teacherRequest.getFirstName());
        createdTeacher.setMiddleName(teacherRequest.getMiddleName());
        createdTeacher.setLastName(teacherRequest.getLastName());
        createdTeacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        createdTeacher.setPhoneNumber(teacherRequest.getPhoneNumber());
        createdTeacher.setAddress(teacherRequest.getAddress());
        createdTeacher.setPrivateEmail(teacherRequest.getPrivateEmail());
        createdTeacher.setSchoolEmail(teacherRequest.getSchoolEmail());
        createdTeacher.setTeacherType(teacherRequest.getTeacherType());
        createdTeacher.setGender(teacherRequest.getGender());
        createdTeacher.setDegree(teacherRequest.getDegree());
        createdTeacher.setNationality(nationalityRequest);

        return teacherRepository.save(createdTeacher);
    }

    @Override
    public Teacher update(TeacherRequest teacherRequest, Integer id) {
        Nationality nationalityRequest = nationalityService.findById(teacherRequest.getNationalityId())
                .orElseThrow(BusinessLogicException::NationalityIdNotFound);
        log.info("Searched nationality id: {}", teacherRequest.getNationalityId());

        Teacher editedTeacher = teacherRepository.findById(id)
                .orElseThrow(BusinessLogicException::TeacherIdNotFound);
        log.info("Searched teacher id: {}", id);

        editedTeacher.setEmployeeCode(teacherRequest.getEmployeeCode());
        editedTeacher.setFirstName(teacherRequest.getFirstName());
        editedTeacher.setMiddleName(teacherRequest.getMiddleName());
        editedTeacher.setLastName(teacherRequest.getLastName());
        editedTeacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        editedTeacher.setPhoneNumber(teacherRequest.getPhoneNumber());
        editedTeacher.setAddress(teacherRequest.getAddress());
        editedTeacher.setPrivateEmail(teacherRequest.getPrivateEmail());
        editedTeacher.setSchoolEmail(teacherRequest.getSchoolEmail());
        editedTeacher.setTeacherType(teacherRequest.getTeacherType());
        editedTeacher.setGender(teacherRequest.getGender());
        editedTeacher.setDegree(teacherRequest.getDegree());
        editedTeacher.setNationality(nationalityRequest);

        return teacherRepository.save(editedTeacher);
    }

    @Override
    public Optional<Teacher> findByEmployeeCode(String teacherCode) {
        return Optional.of(teacherRepository.findTeacherByEmployeeCode(teacherCode));
    }

    @Override
    public Optional<Teacher> findById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void deleteByEmployeeCode(String teacherCode) {
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
