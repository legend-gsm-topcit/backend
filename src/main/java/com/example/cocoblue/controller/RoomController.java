package com.example.cocoblue.controller;

import com.example.cocoblue.dto.EditOption;
import com.example.cocoblue.dto.GameStatus;
import com.example.cocoblue.dto.JoinedMemberList;
import com.example.cocoblue.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoomController {

    private final CreateRoomService createRoomService;
    private final JoinRoomService joinRoomService;
    private final SetKeywordService setKeywordService;
    private final LeaveRoomService leaveRoomService;
    private final EditRoomOptionService editRoomOptionService;
    private final StartGameService startGameService;
    private final FindKeywordListService findKeywordListService;
    private final ChatService chatService;
    private final NextDrawerService nextDrawerService;

    @MessageMapping("/room/{roomId}/create")
    @SendTo("/sub/room/{roomId}/memberList")
    public JoinedMemberList createRoom(
            @DestinationVariable UUID roomId,
            @Payload String name
    ) {
        return createRoomService.createRoom(roomId, name);
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
    @SendTo("/sub/room/{roomId}/deadline")
    public LocalDateTime setKeyword(
            @DestinationVariable UUID roomId,
            @Payload String keyword
    ) {
        return setKeywordService.setKeyword(roomId, keyword);
    }

    @MessageMapping("/room/{roomId}/leave")
    @SendTo("/sub/room/{roomId}/memberList")
    public JoinedMemberList leaveRoom(
            @DestinationVariable UUID roomId,
            @Payload String name
    ) {
        return leaveRoomService.leaveRoom(roomId, name);
    }

    @MessageMapping("/room/{roomId}/option/edit/{name}")
    @SendTo("/sub/room/{roomId}/option")
    public EditOption editRoomOption(
            @DestinationVariable UUID roomId,
            @DestinationVariable String name,
            @Payload EditOption editOption
    ) {
        return editRoomOptionService.editRoomOption(roomId, name, editOption);
    }

    @MessageMapping("/room/{roomId}/start")
    @SendTo("/sub/room/{roomId}/start")
    public GameStatus startGame(
            @DestinationVariable UUID roomId,
            @Payload String name
    ) {
        return startGameService.startGame(roomId, name);
    }

    @MessageMapping("/room/{roomId}/keywordList")
    @SendTo("/sub/room/{roomId}/keywordList")
    public List<String> findKeywordList(
            @DestinationVariable UUID roomId
    ) {
        return findKeywordListService.findKeywordList(roomId);
    }

    @MessageMapping("/room/{roomId}/draw")
    @SendTo("/sub/room/{roomId}/draw")
    public String broadcastDrawing(
            @Payload String picture
    ) {
        return picture;
    }

    @MessageMapping("/room/{roomId}/chat/{memberName}")
    @SendTo("/sub/room/{roomId}/chat")
    public String broadcastChatting(
            @DestinationVariable UUID roomId,
            @DestinationVariable String memberName,
            @Payload String message
    ) {
        return chatService.broadcastChat(roomId, memberName, message);
    }

    @MessageMapping("/room/{roomId}/next")
    public void nextDrawer(
            @DestinationVariable UUID roomId
    ) {
        nextDrawerService.nextDrawer(roomId);
    }
}
