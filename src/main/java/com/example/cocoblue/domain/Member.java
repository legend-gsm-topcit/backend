package com.example.cocoblue.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private String name;
    private Long score;
    private boolean hasDrawn;
    private boolean answerMatched;

    public void updateHasDrawn(boolean hasDrawn) {
        this.hasDrawn = hasDrawn;
    }
    public void updateAnswerMatched(boolean answerMatched) { this.answerMatched = answerMatched; }
    public void increaseScore(Long score) { this.score += score; }
}
