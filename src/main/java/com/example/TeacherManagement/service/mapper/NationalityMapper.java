package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.service.dto.NationalityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NationalityMapper {
    NationalityMapper INSTANCE = Mappers.getMapper(NationalityMapper.class);

    NationalityDto toDto(Nationality nationality);
    List<NationalityDto> toDtos(List<Nationality> nationalities);
}
