package com.example.cocoblue.service;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.RoomRepository;
import com.example.cocoblue.util.RoundManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NextDrawerService {
    private final RoomRepository roomRepository;
    private final RoundManager roundManager;

    public void nextDrawer(UUID roomId) {
        Room room = roomRepository.getRoom(roomId);

        roundManager.nextDrawer(roomId, room.getCurrentDrawerName());
    }
}
