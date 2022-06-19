package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.ClazzRequest;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.exception.MyException;
import com.example.TeacherManagement.repository.ClazzRepository;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private final ClazzRepository clazzRepository;

    @Override
    public List<Clazz> getAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Clazz create(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public Clazz create(ClazzRequest clazzRequest) {
        Clazz createdClazz = new Clazz();
        createdClazz.setCourseBook(clazzRequest.getCourseBook());
        createdClazz.setStartDate(clazzRequest.getStartDate());
        createdClazz.setEndDate(clazzRequest.getEndDate());
        createdClazz.setNumberOfStudent(clazzRequest.getNumberOfStudent());
        createdClazz.setClassId(clazzRequest.getClassId());
        createdClazz.setTotalCourseHours(clazzRequest.getTotalCourseHours());

        return clazzRepository.save(createdClazz);
    }

    @Override
    public Clazz update(ClazzRequest clazzRequest, Integer id){
        Clazz editedClazz = clazzRepository.findById(id)
                .orElseThrow(MyException::ClassIdNotFound);

        editedClazz.setClassId(clazzRequest.getClassId());
        editedClazz.setNumberOfStudent(clazzRequest.getNumberOfStudent());
        editedClazz.setStartDate(clazzRequest.getStartDate());
        editedClazz.setEndDate(clazzRequest.getEndDate());
        editedClazz.setTotalCourseHours(clazzRequest.getTotalCourseHours());
        editedClazz.setCourseBook(clazzRequest.getCourseBook());

        return clazzRepository.save(editedClazz);
    }

    @Override
    public Optional<Clazz> findByClassId(String classId) {
        return Optional.of(clazzRepository.findClassByClassId(classId));
    }

    @Override
    public Optional<Clazz> findById(Integer id) {
        return clazzRepository.findById(id);
    }

    @Override
    public void deleteByClassId(String classId) {
        clazzRepository.delete(clazzRepository.findClassByClassId(classId));
    }

    @Override
    public void deleteById(Integer id) {
        clazzRepository.deleteById(id);
    }

    @Override
    public List<ClazzHaveNotBeenAssignedDto> findClassesThatHaveNotBeenAssigned() {
        return clazzRepository.findClassesThatHaveNotBeenAssigned();
    }
}
