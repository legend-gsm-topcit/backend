package com.example.cocoblue.service;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.GameStatus;
import com.example.cocoblue.repository.KeywordRepository;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartGameService {

    private final RoomRepository roomRepository;
    private final KeywordRepository keywordRepository;

    public GameStatus startGame(UUID roomId, String name) {
        Room room = roomRepository.getRoom(roomId);
        log.info("at startGame " + roomId + name + room.getCurrentDrawerName());

        if (!name.equals(room.getOwnerName())) {
            // todo exception 처리하기
        }
        if (room.getMembers().size() < Room.MIN_MEMBER_COUNT) {
            // todo: do something
        }

        keywordRepository.storeUnionOfSets(roomId.toString(), room.getLevel().name());

        room.increaseRoundCount();

        Optional<Member> nextDrawer = room.getMembers().values()
                .stream()
                .filter(drawer -> !drawer.isHasDrawn())
                .findAny();
        String nextDrawerName = nextDrawer.map(Member::getName).orElse(null);
        room.setNextDrawer(nextDrawerName);

        log.info("at startGame " + room.getCurrentDrawerName());

        return GameStatus.builder()
                .roundCount(room.getRoundCount())
                .nextDrawer(nextDrawerName)
                .isFinished(false)
                .build();
    }
}
