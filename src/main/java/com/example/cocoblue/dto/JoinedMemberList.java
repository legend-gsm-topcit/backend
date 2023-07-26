package com.example.cocoblue.dto;

import com.example.cocoblue.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class JoinedMemberList {

    private String ownerName;
    private List<Member> memberList;
}
