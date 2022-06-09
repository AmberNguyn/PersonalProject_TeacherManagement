package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Room;
import com.example.TeacherManagement.repository.RoomRepository;
import com.example.TeacherManagement.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    @Autowired
    private final RoomRepository roomRepository;

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> findRoomByRoomNumber(Integer roomNumber) {
        return Optional.of(roomRepository.findRoomByRoomNumber(roomNumber));
    }

    @Override
    public void deleteRoomByRoomNumber(Integer roomNumber) {
        roomRepository.delete(roomRepository.findRoomByRoomNumber(roomNumber));
    }
}
