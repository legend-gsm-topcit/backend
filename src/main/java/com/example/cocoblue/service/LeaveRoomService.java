package com.example.cocoblue.service;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.JoinedMemberList;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeaveRoomService {

    private final RoomRepository roomRepository;

    public JoinedMemberList leaveRoom(UUID roomId, String name) {
        Room room = roomRepository.getRoom(roomId);
        room.getMembers().remove(name);

        List<Member> memberList = new ArrayList<>(room.getMembers().values());

        return JoinedMemberList.builder()
                .ownerName(room.getOwnerName())
                .memberList(memberList)
                .build();
    }
}
