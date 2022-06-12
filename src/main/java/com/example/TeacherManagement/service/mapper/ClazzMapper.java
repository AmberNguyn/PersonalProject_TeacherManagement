package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.service.dto.ClazzDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClazzMapper {
    ClazzMapper INSTANCE = Mappers.getMapper(ClazzMapper.class);

    ClazzDto toDto(Clazz clazz);
    List<ClazzDto> toDtos(List<Clazz> clazzes);
}
