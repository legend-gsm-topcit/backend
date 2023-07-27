package com.example.cocoblue.service;

import com.example.cocoblue.domain.Level;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.KeywordRepository;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindKeywordListService {

    private final RoomRepository roomRepository;
    private final KeywordRepository keywordRepository;

    public List<String> findKeywordList(UUID roomId) {

        log.info("at findKeywordList " + roomId);

        Room room = roomRepository.getRoom(roomId);
        List<String> keywordList = keywordRepository.popRandomMemberFromSet(roomId.toString());

        log.info("at findKeywordList " + keywordList.get(0) + keywordList.get(1) + keywordList.get(2) + keywordList.get(3));

        return keywordRepository.popRandomMemberFromSet(roomId.toString());
    }
}
