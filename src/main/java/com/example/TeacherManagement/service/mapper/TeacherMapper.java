package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.service.dto.TeacherDto;
import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    //from teacher
    @Mapping(target = "fullName", expression = "java(teacher.getLastName() + \"\" + teacher.getMiddleName() + \"\" + teacher.getFirstName())")


    //from nationality
    @Mapping(source = "nationality.country", target = "nationality")

    TeacherDto toDto(Teacher teacher);
    List<TeacherDto> toDtos(List<Teacher> teachers);

    @AfterMapping
    default void setFullName(Teacher teacher, @MappingTarget TeacherDto target){
        target.setFullName(teacher.getFirstName() + " " + teacher.getMiddleName() + " " + teacher.getLastName());
    }
}
