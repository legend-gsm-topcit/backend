package com.example.cocoblue.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final SetOperations<String, String> setOperations;

    @Autowired
    public KeywordRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.setOperations = redisTemplate.opsForSet();
    }

    public List<String> popRandomMemberFromSet(String key) {
        return setOperations.pop(key, 4L);
    }

    public void storeUnionOfSets(String unionSetKey, String... sourceSetKeys) {
        setOperations.unionAndStore(List.of(sourceSetKeys), unionSetKey);
    }

    public void remove(String roomId) {
        redisTemplate.delete(roomId);
    }
}
