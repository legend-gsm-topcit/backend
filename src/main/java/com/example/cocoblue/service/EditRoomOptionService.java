package com.example.cocoblue.service;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.EditOption;
import com.example.cocoblue.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditRoomOptionService {

    private final RoomRepository roomRepository;

    public EditOption editRoomOption(UUID roomId, String name, EditOption editOption) {
        Room room = roomRepository.getRoom(roomId);

        if (!name.equals(room.getOwnerName())) {
            // todo exception 처리하기
        }

        room.updateRoomOption(
                editOption.maxMemberCount(),
                editOption.maxRoundCount(),
                editOption.level()
        );

        return editOption;
    }
}
