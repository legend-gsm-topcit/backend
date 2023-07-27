package com.example.cocoblue.service;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.JoinedMemberList;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaveRoomService {

    private final RoomRepository roomRepository;

    public JoinedMemberList leaveRoom(UUID roomId, String name) {
        log.info("at leaveRoom " + roomId.toString() + name);
        Room room = roomRepository.getRoom(roomId);
        room.getMembers().remove(name);

        List<Member> memberList = new ArrayList<>(room.getMembers().values());

        log.info("at leaveRoom " + memberList.get(0).getName());


        return JoinedMemberList.builder()
                .ownerName(room.getOwnerName())
                .memberList(memberList)
                .build();
    }
}
