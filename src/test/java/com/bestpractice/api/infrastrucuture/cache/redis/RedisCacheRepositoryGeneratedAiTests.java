package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RedisCacheRepositoryGeneratedAiTests {

    private RedisCacheRepository redisCacheRepository;

    @BeforeEach
    void setUp() {
        redisCacheRepository = new RedisCacheRepository();
    }

    @Test
    void testGetInstance() {
        redisCacheRepository.getInstance();
    }

    @Test
    void testGetCache() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", 123);
        redisCacheRepository.getCache(data);
    }

    @Test
    void testSetCache() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", 123);
        redisCacheRepository.setCache(data);
    }

    @Test
    void testClearCache() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", 123);
        redisCacheRepository.setCache(data);
        redisCacheRepository.clearCache();
    }
}
