package com.example.cocoblue.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Room {

    public static Long MAX_MEMBER_COUNT = 12L;
    public static Long MAX_ROUND_COUNT = 10L;

    private Long maxMemberCount;
    private Long maxRoundCount;
    private Long roundCount;
    private Level level;
    private String ownerName;
    private String currentDrawerName;
    private HashMap<String, Member> members;
    private String keyword;
    private List<String> keywordBlackList;

    public void updateKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void updateRoomOption(Long maxMemberCount, Long maxRoundCount, Level level) {
        this.maxMemberCount = maxMemberCount;
        this.maxRoundCount = maxRoundCount;
        this.level = level;
    }
}
