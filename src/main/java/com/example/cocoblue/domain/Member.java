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
}
