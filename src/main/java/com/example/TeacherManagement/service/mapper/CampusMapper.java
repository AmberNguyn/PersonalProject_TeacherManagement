package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Campus;
import com.example.TeacherManagement.service.dto.CampusDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampusMapper {
    CampusMapper INSTANCE = Mappers.getMapper(CampusMapper.class);

    CampusDto toDto(Campus campus);
    List<CampusDto> toDtos(List<Campus> campuses);

}
