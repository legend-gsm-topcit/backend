package com.example.cocoblue.service;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.RoomRepository;
import com.example.cocoblue.util.RoundManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NextDrawerService {
    private final RoomRepository roomRepository;
    private final RoundManager roundManager;

    public void nextDrawer(UUID roomId) {
        log.info("at nextDrawer " + roomId);

        Room room = roomRepository.getRoom(roomId);

        roundManager.nextDrawer(roomId, room.getCurrentDrawerName());

        log.info("at nextDrawer " + room.getCurrentDrawerName());
    }
}
