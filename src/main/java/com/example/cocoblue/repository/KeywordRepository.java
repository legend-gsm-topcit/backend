package com.example.cocoblue.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KeywordRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final SetOperations<String, String> setOperations;

//    public RedisService(RedisTemplate<String, String> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        this.setOperations = redisTemplate.opsForSet();
//    }

    private List<String> popRandomMemberFromSet(String key) {
        return setOperations.pop(key, 4L);
    }
}
