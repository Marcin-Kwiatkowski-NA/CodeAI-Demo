package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.MockitoExtension.MockitoExtension;

@MockitoExtension
class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The host property is set to "localhost"
        redisProperty.setHost("localhost");
        // THEN: The host property is set to "localhost"
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The port property is set to 16384
        redisProperty.setPort(16384);
        // THEN: The port property is set to 16384
        assertEquals(16384, redisProperty.getPort());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The password property is set to "mySecretPassword"
        redisProperty.setPassword("mySecretPassword");
        // THEN: The password property is set to "mySecretPassword"
        assertEquals("mySecretPassword", redisProperty.getPassword());
    }
}
