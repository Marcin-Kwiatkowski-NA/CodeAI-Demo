package com.bestpractice.api.infrastrucuture.cache.redis;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(RedisTestExtension.class)
public class RedisCacheRepositoryTest {

    private RedisCacheRepository redisCache;

    @BeforeAll
    public void setUpBeforeAll() {
        redisCache = new RedisCacheRepository();
    }

    @BeforeEach
    public void setUpBeforeEach() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1L);
        data.put("name", "John Doe");
        redisCache.setData(data);
    }

    @AfterEach
    public void tearDownAfterEach() {
        redisCache.evictData("1");
    }

    @Test
    public void testGetUserDataById() {
        User user = redisCache.getUserDataById(1L);
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testGetUserDataById_NotFound() {
        assertThrows(RuntimeException.class, () -> redisCache.getUserDataById(2L));
    }
}