package com.example.cocoblue.repository;

import com.example.cocoblue.domain.Room;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class RoomRepository {

    private final HashMap<UUID, Room> rooms = new HashMap<>();

    public Room getRoom(UUID roomId) {
        return rooms.get(roomId);
    }

    public void setRoom(UUID roomId, Room room) {
        rooms.put(roomId, room);
    }

    public void deleteRoom(UUID roomId) {
        rooms.remove(roomId);
    }
}
