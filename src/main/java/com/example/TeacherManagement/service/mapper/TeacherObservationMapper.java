package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.TeacherObservation;
import com.example.TeacherManagement.service.dto.TeacherObservationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherObservationMapper {
    TeacherObservationMapper INSTANCE = Mappers.getMapper(TeacherObservationMapper.class);

    @Mapping(source = "assignmentDetail.teacher.employeeCode", target = "teacherCode")
    @Mapping(source = "assignmentDetail.teacher.firstName", target = "firstName")
    @Mapping(source = "assignmentDetail.teacher.lastName", target = "lastName")
    @Mapping(source = "assignmentDetail.teacher.teacherType", target = "teacherType")

    @Mapping(source = "assignmentDetail.lesson", target = "lesson")

    @Mapping(source = "trainingQualityManager.employeeCode", target = "TQMCode")
    @Mapping(source = "trainingQualityManager.firstName", target = "TQMFirstName")
    @Mapping(source = "trainingQualityManager.lastName", target = "TQMLastName")


    TeacherObservationDto toDto(TeacherObservation teacherObservation);
    List<TeacherObservationDto> toDtos(List<TeacherObservation> teacherObservations);
}
