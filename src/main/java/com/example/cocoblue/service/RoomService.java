package com.example.cocoblue.service;

import com.example.cocoblue.domain.Level;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.domain.Member;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class RoomService {

    HashMap<UUID, Room> rooms = new HashMap<>();

    public void createRoom(UUID roomId, String name) {
        Room room = Room.builder()
                .maxMemberCount(Room.MAX_MEMBER_COUNT)
                .maxRoundCount(Room.MAX_ROUND_COUNT)
                .level(Level.NORMAL)
                .ownerName(name)
                .currentDrawerName(null)
                .members(new HashMap<>())
                .build();

        Member member = Member.builder()
                .name(name)
                .score(0L)
                .hasDrawn(false)
                .build();

        room.getMembers().put(name, member);
        rooms.put(roomId, room);
    }


}
