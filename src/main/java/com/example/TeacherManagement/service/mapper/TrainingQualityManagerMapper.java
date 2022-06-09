package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.TrainingQualityManager;
import com.example.TeacherManagement.service.dto.TrainingQualityManagerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TrainingQualityManagerMapper {
    TrainingQualityManagerMapper INSTANCE = Mappers.getMapper(TrainingQualityManagerMapper.class);

    TrainingQualityManagerDto toDto(TrainingQualityManager trainingQualityManager);
    List<TrainingQualityManagerDto> toDtos(List<TrainingQualityManager> trainingQualityManagers);
}
