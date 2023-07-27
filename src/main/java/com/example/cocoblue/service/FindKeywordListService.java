package com.example.cocoblue.service;

import com.example.cocoblue.domain.Level;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.KeywordRepository;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindKeywordListService {

    private final RoomRepository roomRepository;
    private final KeywordRepository keywordRepository;

    public List<String> findKeywordList(UUID roomId) {
        Room room = roomRepository.getRoom(roomId);
        return keywordRepository.popRandomMemberFromSet(roomId.toString());
    }
}
