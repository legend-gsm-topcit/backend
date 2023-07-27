package com.example.cocoblue.service;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.EditOption;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditRoomOptionService {

    private final RoomRepository roomRepository;

    public EditOption editRoomOption(UUID roomId, String name, EditOption editOption) {
        Room room = roomRepository.getRoom(roomId);
        log.info("at editRoomOption " + roomId.toString() + name + room.getLevel());

        if (room.getRoundCount() == 0) {
            // todo exception 처리하기
        }

        if (!name.equals(room.getOwnerName())) {
            // todo exception 처리하기
        }

        room.updateRoomOption(
                editOption.maxMemberCount(),
                editOption.maxRoundCount(),
                editOption.level()
        );

        log.info("at editRoomOption " + room.getLevel());

        return editOption;
    }
}
