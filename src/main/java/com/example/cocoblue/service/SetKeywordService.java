package com.example.cocoblue.service;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetKeywordService {

    private final RoomRepository roomRepository;
    private final SimpMessageSendingOperations sendingOperations;

    public LocalDateTime setKeyword(UUID roomId, String keyword) {
        Room room = roomRepository.getRoom(roomId);
        room.updateKeyword(keyword);
        room.setDeadLine(LocalDateTime.now().plusMinutes(1));
        // todo: might have to return deadLine

        return room.getDeadLine();
    }
}
