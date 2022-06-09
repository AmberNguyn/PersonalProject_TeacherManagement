package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Class;
import com.example.TeacherManagement.service.dto.ClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClassMapper {
    ClassMapper INSTANCE = Mappers.getMapper(ClassMapper.class);

    ClassDto toDto(Class clazz);
    List<ClassDto> toDtos(List<Class> clazzes);
}
