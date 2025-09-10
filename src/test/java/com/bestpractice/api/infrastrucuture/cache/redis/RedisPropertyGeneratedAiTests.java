package com.bestpractice.api.infrastrucuture.cache.redis;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ExtendWith.withExtension;

@ExtendWith(withExtension(RedisPropertyExtension.class))
class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void getSetHost() {
        // GIVEN: RedisProperty instance is created
        // WHEN: host property is set to "localhost"
        redisProperty.setHost("localhost");
        // THEN: host property is set to "localhost"
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void getSetPort() {
        // GIVEN: RedisProperty instance is created
        // WHEN: port property is set to 6379
        redisProperty.setPort(6379);
        // THEN: port property is set to 6379
        assertEquals(6379, redisProperty.getPort());
    }

    @Test
    void getSetPassword() {
        // GIVEN: RedisProperty instance is created
        // WHEN: password property is set to "secretPassword"
        redisProperty.setPassword("secretPassword");
        // THEN: password property is set to "secretPassword"
        assertEquals("secretPassword", redisProperty.getPassword());
    }
}