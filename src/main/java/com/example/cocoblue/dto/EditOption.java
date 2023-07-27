package com.example.cocoblue.dto;

import com.example.cocoblue.domain.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record EditOption(

        Long maxMemberCount,
        Long maxRoundCount,
        Level level
){
}
