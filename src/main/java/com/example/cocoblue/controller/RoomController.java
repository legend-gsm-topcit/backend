package com.example.cocoblue.controller;

import com.example.cocoblue.dto.JoinedMemberList;
import com.example.cocoblue.service.CreateRoomService;
import com.example.cocoblue.service.JoinRoomService;
import com.example.cocoblue.service.SetKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final CreateRoomService createRoomService;
    private final JoinRoomService joinRoomService;
    private final SetKeywordService setKeywordService;

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
}
