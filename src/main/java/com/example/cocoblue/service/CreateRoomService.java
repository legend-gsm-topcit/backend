package com.example.cocoblue.service;

import com.example.cocoblue.domain.Level;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.domain.Member;
import com.example.cocoblue.dto.JoinedMemberList;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateRoomService {

    private final RoomRepository roomRepository;

    public JoinedMemberList createRoom(UUID roomId, String name) {
        Room room = Room.builder()
                .maxMemberCount(Room.MAX_MEMBER_COUNT)
                .maxRoundCount(Room.MAX_ROUND_COUNT)
                .roundCount(0L)
                .level(Level.NORMAL)
                .ownerName(name)
                .currentDrawerName(null)
                .members(new HashMap<>())
                .keyword(null)
                .build();

        Member member = Member.builder()
                .name(name)
                .score(0L)
                .answerMatched(false)
                .hasDrawn(false)
                .build();

        log.info("at createRoom " + roomId.toString() + name);

        room.getMembers().put(name, member);
        roomRepository.setRoom(roomId, room);

        return JoinedMemberList.builder()
                .ownerName(room.getOwnerName())
                .memberList((List.of(member)))
                .build();
    }
}
