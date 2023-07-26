package com.example.cocoblue.service;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetKeywordService {

    private final RoomRepository roomRepository;

    public void setKeyword(UUID roomId, String keyword) {
        Room room = roomRepository.getRoom(roomId);
        room.updateKeyword(keyword);
    }
}
