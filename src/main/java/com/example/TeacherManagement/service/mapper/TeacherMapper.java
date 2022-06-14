package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.service.dto.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    //from nationality
    @Mapping(source = "nationality.country", target = "nationality")

    TeacherDto toDto(Teacher teacher);
    List<TeacherDto> toDtos(List<Teacher> teachers);
}
