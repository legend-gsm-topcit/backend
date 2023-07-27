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
public class JoinRoomService {

    private final RoomRepository roomRepository;

    public JoinedMemberList joinRoom(UUID roomId, String name) {
        log.info("at joinRoom " + roomId.toString() + name);
        Room room = roomRepository.getRoom(roomId);

        if(room.getMembers().containsKey(name)) {
            // todo exception 처리하기
        }

        Member member = Member.builder()
                .name(name)
                .score(0L)
                .answerMatched(false)
                .hasDrawn(false)
                .build();

        room.getMembers().put(member.getName(), member);
        List<Member> memberList = new ArrayList<>(room.getMembers().values());

        log.info("at joinRoom " + memberList.get(0).getName());

        return JoinedMemberList.builder()
                .ownerName(room.getOwnerName())
                .memberList(memberList)
                .build();
    }
}
