package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.service.dto.CertificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CertificationMapper {
    CertificationMapper INSTANCE = Mappers.getMapper(CertificationMapper.class);

    CertificationDto toDto(Certification certification);
    List<CertificationDto> toDtos(List<Certification> certifications);

}
