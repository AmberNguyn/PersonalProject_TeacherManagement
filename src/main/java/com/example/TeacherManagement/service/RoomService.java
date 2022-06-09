package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getAll();

    Room addRoom(Room room);

    Optional<Room> findRoomByRoomNumber(Integer roomNumber);

    void deleteRoomByRoomNumber(Integer roomNumber);

}
