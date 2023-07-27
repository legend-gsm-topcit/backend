package com.example.cocoblue.service;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.RoomRepository;
import com.example.cocoblue.util.RoundManager;
import com.example.cocoblue.util.ScoreBoardManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;
    private final RoundManager roundManager;
    private final ScoreBoardManager scoreBoardManager;

    public String broadcastChat(UUID roomId, String name, String message) {

        log.info("at broadcastChat " + roomId + name + " " + message);

        Room room = roomRepository.getRoom(roomId);
        Member member = room.getMembers().get(name);

        if(member.getName().equals(room.getOwnerName()) && message.equals("/게임 종료")) {
            scoreBoardManager.broadcastScoreBoard(roomId, room);
        }

        boolean answerMatches = message.equals(room.getKeyword());
        log.info("at broadcastChat " + Boolean.toString(answerMatches));

        Duration timeLeft = Duration.between(room.getDeadLine(), LocalDateTime.now());
        if (!answerMatches || timeLeft.isNegative() || member.isAnswerMatched()) {
            return createChatMessage(name, message);
        }

        Long scoreToAdd = timeLeft.toSeconds() * 10;
        member.increaseScore(scoreToAdd);
        member.updateAnswerMatched(true);

        boolean allMatches = room.getMembers().values().stream().allMatch(Member::isAnswerMatched);
        log.info("at broadcastChat " + Boolean.toString(allMatches));
        if (allMatches) {
            roundManager.nextDrawer(roomId, room.getCurrentDrawerName());
            return createCorrectMessage(name, scoreToAdd) + "\n" + createAnswerMessage(room.getKeyword());
        }

        return createCorrectMessage(name, scoreToAdd);
    }

    private String createChatMessage(String name, String message) {
        return name + " : " + message;
    }

    private String createCorrectMessage(String name, Long score) {
        return name + "님께서 맞혀서" + score + "점을 얻었습니다!";
    }

    private String createAnswerMessage(String keyword) {
        return "정답은 " + keyword + "였습니다!";
    }
}
