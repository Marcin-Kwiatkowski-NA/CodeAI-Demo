package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
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
        // THEN: The host property should be "localhost"
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The port property is set to 16384
        redisProperty.setPort(16384);
        // THEN: The port property should be 16384
        assertEquals(16384, redisProperty.getPort());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The password property is set to "mySecretPassword"
        redisProperty.setPassword("mySecretPassword");
        // THEN: The password property should be "mySecretPassword"
        assertEquals("mySecretPassword", redisProperty.getPassword());
    }

    @Test
    void testSetAllProperties() {
        // GIVEN: A new RedisProperty instance
        // WHEN: All properties (host, port, password) are set
        redisProperty.setHost("redis-server");
        redisProperty.setPort(6379);
        redisProperty.setPassword("securePassword");
        // THEN: All properties should be set correctly
        assertEquals("redis-server", redisProperty.getHost());
        assertEquals(6379, redisProperty.getPort());
        assertEquals("securePassword", redisProperty.getPassword());
    }
}
