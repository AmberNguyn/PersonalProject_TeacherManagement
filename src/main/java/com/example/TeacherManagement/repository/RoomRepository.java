package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findRoomByRoomNumber(Integer roomNumber);
}
