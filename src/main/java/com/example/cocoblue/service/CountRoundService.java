package com.example.cocoblue.service;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.GameStatus;
import com.example.cocoblue.repository.KeywordRepository;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountRoundService {

    private final RoomRepository roomRepository;
    private final KeywordRepository keywordRepository;

    public GameStatus countRound(UUID roomId, String name) {
        Room room = roomRepository.getRoom(roomId);
        Member member = room.getMembers().get(name);
        member.updateHasDrawn(true);

        Optional<Member> nextDrawer = room.getMembers().values()
                .stream()
                .filter(drawer -> !drawer.isHasDrawn())
                .findAny();

        String nextDrawerName = nextDrawer.map(Member::getName).orElse(null);

        boolean isFinished = nextDrawer.isEmpty() && room.getMaxRoundCount() < room.getRoundCount();
        if (isFinished) {
            removeAll(roomId);
        }

        return GameStatus.builder()
                .roundCount(room.getRoundCount())
                .nextDrawer(nextDrawerName)
                .isFinished(isFinished)
                .build();
    }

    private void removeAll(UUID roomId) {
        keywordRepository.remove(roomId.toString());
        roomRepository.deleteRoom(roomId);
    }
}
