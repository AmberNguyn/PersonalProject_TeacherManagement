package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.entity.CertificationDetail;
import com.example.TeacherManagement.service.dto.CertificationDetailDto;
import com.example.TeacherManagement.service.dto.CertificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CertificationDetailMapper {
    CertificationDetailMapper INSTANCE = Mappers.getMapper(CertificationDetailMapper.class);


    CertificationDetailDto toDto(CertificationDetail certificationDetail);
    List<CertificationDetailDto> toDtos(List<CertificationDetail> certificationDetails);


}
