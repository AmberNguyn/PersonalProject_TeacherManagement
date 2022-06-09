package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Room;
import com.example.TeacherManagement.service.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDto toDto(Room room);
    List<RoomDto> toDtos(List<Room> rooms);
}
