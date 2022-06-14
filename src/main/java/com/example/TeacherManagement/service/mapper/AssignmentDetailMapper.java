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

    @Mapping(source = "contract.teacher.employeeCode", target = "teacherCode")
    @Mapping(source = "contract.teacher.firstName", target = "firstName")
    @Mapping(source = "contract.teacher.lastName", target = "lastName")
    @Mapping(source = "contract.teacher.teacherType", target = "teacherType")
    @Mapping(source = "contract.teacher.phoneNumber", target = "phoneNumber")


    @Mapping(source = "clazz.classId", target = "classId")
    @Mapping(source = "clazz.startDate", target = "startDate")
    @Mapping(source = "clazz.endDate", target = "endDate")
    @Mapping(source = "clazz.totalCourseHours", target = "totalCourseHours")


    AssignmentDetailDto toDto(AssignmentDetail assignmentDetail);
    List<AssignmentDetailDto> toDtos(List<AssignmentDetail> assignmentDetails);
}
