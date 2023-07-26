package com.example.cocoblue.controller;

import com.example.cocoblue.domain.Room;
import com.example.cocoblue.dto.EditOption;
import com.example.cocoblue.dto.JoinedMemberList;
import com.example.cocoblue.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final CreateRoomService createRoomService;
    private final JoinRoomService joinRoomService;
    private final SetKeywordService setKeywordService;
    private final LeaveRoomService leaveRoomService;
    private final EditRoomOptionService editRoomOptionService;

    @MessageMapping("/room/{roomId}/create")
    public void createRoom(
            @DestinationVariable UUID roomId,
            @Payload String name
    ) {
        createRoomService.createRoom(roomId, name);
    }

    @MessageMapping("/room/{roomId}/join")
    @SendTo("/sub/room/{roomId}/memberList")
    public JoinedMemberList joinRoom(
            @DestinationVariable UUID roomId,
            @Payload String name
    ) {
        return joinRoomService.joinRoom(roomId, name);
    }

    @MessageMapping("/room/{roomId}/keyword")
    public void setKeyword(
            @DestinationVariable UUID roomId,
            @Payload String keyword
    ) {
        setKeywordService.setKeyword(roomId, keyword);
    }

    @MessageMapping("/room/{roomId}/leave")
    @SendTo("/sub/room/{roomId}/memberList")
    public JoinedMemberList leaveRoom(
            @DestinationVariable UUID roomId,
            @Payload String name
    ) {
        return leaveRoomService.leaveRoom(roomId, name);
    }

    @MessageMapping("/room/{roomId}/option/edit")
    @SendTo("/sub/room/{roomId}/option")
    public EditOption editRoomOption(
            @DestinationVariable UUID roomId,
            @Payload EditOption editOption
    ) {
        return editRoomOptionService.editRoomOption(roomId, editOption);
    }
}
