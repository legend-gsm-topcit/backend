package com.example.cocoblue.service;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StartGameService {

    private final RoomRepository roomRepository;

    public boolean startGame(UUID roomId, String name) {
        Room room = roomRepository.getRoom(roomId);

        if (!name.equals(room.getOwnerName())) {
            // todo exception 처리하기
        }

        room.increaseRoundCount();

        return true;
    }
}
