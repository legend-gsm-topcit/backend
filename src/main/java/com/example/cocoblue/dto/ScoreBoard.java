package com.example.cocoblue.dto;

import com.example.cocoblue.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ScoreBoard {
    private List<Member> memberList;
}
