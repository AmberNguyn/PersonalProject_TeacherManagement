package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.RoomService;
import com.example.TeacherManagement.service.dto.RoomDto;
import com.example.TeacherManagement.service.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(RoomResource.PATH)
public class RoomResource {
    @Autowired
    private RoomService roomService;

    public static final String PATH = "/api/room";

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAll() {
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDtos(roomService.getAll()));
    }
}
