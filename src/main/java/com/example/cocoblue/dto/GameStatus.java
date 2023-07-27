package com.example.cocoblue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GameStatus {

    private Long roundCount;
    private String nextDrawer;
    private boolean isFinished;
}
