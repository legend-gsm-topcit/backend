package com.example.cocoblue.service;

import com.example.cocoblue.domain.Member;
import com.example.cocoblue.domain.Room;
import com.example.cocoblue.repository.RoomRepository;
import com.example.cocoblue.util.RoundManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;
    private final RoundManager roundManager;

    public String broadcastChat(UUID roomID, String name, String message) {
        Room room = roomRepository.getRoom(roomID);
        Member member = room.getMembers().get(name);

        boolean answerMatches = message.equals(room.getKeyword());
        Duration timeLeft = Duration.between(room.getDeadLine(), LocalDateTime.now());
        if (!answerMatches || timeLeft.isNegative() || member.isAnswerMatched()) {
            return createChatMessage(name, message);
        }

        Long scoreToAdd = timeLeft.toSeconds() * 10;
        member.increaseScore(scoreToAdd);
        member.updateAnswerMatched(true);

        boolean allMatches = room.getMembers().values().stream().allMatch(Member::isAnswerMatched);
        if (allMatches) {
            roundManager.nextUser(roomID, room.getCurrentDrawerName());
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
