package com.example.cocoblue.util;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.GameStatus;
import com.example.cocoblue.repository.KeywordRepository;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoundManager {

    private final RoomRepository roomRepository;
    private final KeywordRepository keywordRepository;
    private final SimpMessageSendingOperations sendingOperations;

    public void nextDrawer(UUID roomId, String name) {
        Room room = roomRepository.getRoom(roomId);

        Member member = room.getMembers().get(name);
        member.updateHasDrawn(true);

        Optional<Member> nextDrawer = room.getMembers().values()
                .stream()
                .filter(drawer -> !drawer.isHasDrawn())
                .findAny();

        String nextDrawerName = nextDrawer.map(Member::getName).orElse(null);
        room.setNextDrawer(nextDrawerName);

        boolean isFinished = nextDrawer.isEmpty() && room.getMaxRoundCount() < room.getRoundCount();
        if (isFinished) {
            removeAll(roomId);
        }

        GameStatus gameStatus = GameStatus.builder()
                .roundCount(room.getRoundCount())
                .nextDrawer(nextDrawerName)
                .isFinished(isFinished)
                .build();
        publishGameStatus(roomId, gameStatus);
    }

    private void removeAll(UUID roomId) {
        keywordRepository.remove(roomId.toString());
        roomRepository.deleteRoom(roomId);
    }

    private void publishGameStatus(UUID roomId, GameStatus status) {
        sendingOperations.convertAndSend("/sub/room/"+roomId+"/round", status);
    }
}
