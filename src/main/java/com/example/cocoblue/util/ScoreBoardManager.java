package com.example.cocoblue.util;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.ScoreBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScoreBoardManager {


    private final SimpMessageSendingOperations sendingOperations;

    public void broadcastScoreBoard(UUID roomId, Room room) {
        List<Member> memberList = room.
                getMembers().
                values().
                stream().
                sorted(Comparator.comparing(Member::getScore).reversed()).collect(Collectors.toList());

        ScoreBoard scoreBoard = ScoreBoard.builder()
                .memberList(memberList)
                .build();

        sendingOperations.convertAndSend("/sub/room/"+roomId+"/scoreBoard", scoreBoard);
    }
}
