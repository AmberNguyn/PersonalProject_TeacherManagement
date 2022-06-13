package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.RoomRequest;
import com.example.TeacherManagement.entity.Room;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.RoomService;
import com.example.TeacherManagement.service.dto.RoomDto;
import com.example.TeacherManagement.service.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(RoomResource.PATH)
public class RoomResource {
    @Autowired
    private RoomService roomService;

    public static final String PATH = "/api/rooms";

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAll() {
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDtos(roomService.getAll()));
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<RoomDto> getRoomByRoomNumber(@PathVariable Integer roomNumber) throws ResourceNotFoundException {
        Room room = roomService.findRoomByRoomNumber(roomNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Room " + roomNumber + " not found!")
                );
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(room));
    }



    @PostMapping
    public ResponseEntity<RoomDto> create(@RequestBody Room roomRequest) {
        Room createdRoom = roomService.addRoom(
                new Room(
                        null,
                        roomRequest.getRoomNumber(),
                        roomRequest.getNumberOfTable(),
                        roomRequest.getRoomSize()
                )
        );
        return ResponseEntity.created(URI.create(RoomResource.PATH + "/" + createdRoom.getId()))
                .body(RoomMapper.INSTANCE.toDto(roomService.addRoom(createdRoom)));
    }

    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> delete(@PathVariable Integer roomNumber) throws ResourceNotFoundException {
        Room room = roomService.findRoomByRoomNumber(roomNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Room " + roomNumber + " not found!")
                );
        roomService.deleteRoomByRoomNumber(roomNumber);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{roomNumber}")
    public ResponseEntity<RoomDto> update(@PathVariable Integer roomNumber,
                                          @RequestBody RoomRequest roomRequest) throws ResourceNotFoundException {

        Room editedRoom = roomService.findRoomByRoomNumber(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException(roomNumber + " not found!"));

        editedRoom.setRoomNumber(roomRequest.getRoomNumber());
        editedRoom.setNumberOfTable(roomRequest.getNumberOfTable());
        editedRoom.setRoomSize(roomRequest.getRoomSize());

        Room updatedRoom = roomService.addRoom(editedRoom);
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(updatedRoom));
    }

}
