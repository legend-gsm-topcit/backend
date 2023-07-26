package com.example.cocoblue.controller;

import com.example.cocoblue.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @MessageMapping("/room/{roomId}")
    public void createRoom(
            @DestinationVariable UUID roomId,
            @Payload String name
    ) {
        roomService.createRoom(roomId, name);
    }
}
