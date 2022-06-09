package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.service.dto.AssignmentDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssignmentDetailMapper {
    AssignmentDetailMapper INSTANCE = Mappers.getMapper(AssignmentDetailMapper.class);

    @Mapping(source = "teacher.employeeCode", target = "teacherCode")
    @Mapping(source = "teacher.firstName", target = "firstName")
    @Mapping(source = "teacher.lastName", target = "lastName")
    @Mapping(source = "teacher.teacherType", target = "teacherType")
    @Mapping(source = "teacher.phoneNumber", target = "phoneNumber")

    @Mapping(source = "room.roomNumber", target = "roomNumber")

    @Mapping(source = "clazz.classId", target = "classId")
    @Mapping(source = "clazz.courseBook", target = "courseBook")

    AssignmentDetailDto toDto(AssignmentDetail assignmentDetail);
    List<AssignmentDetailDto> toDtos(List<AssignmentDetail> assignmentDetails);
}
