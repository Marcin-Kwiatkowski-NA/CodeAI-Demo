package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    @DisplayName("Test setting and getting host")
    void testSetAndGetHost() {
        // GIVEN: Initialize RedisProperty
        // WHEN: Set host to "localhost"
        redisProperty.setHost("localhost");
        // THEN: Verify host is set correctly
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    @DisplayName("Test setting and getting port")
    void testSetAndGetPort() {
        // GIVEN: Initialize RedisProperty
        // WHEN: Set port to 6379
        redisProperty.setPort(6379);
        // THEN: Verify port is set correctly
        assertEquals(6379, redisProperty.getPort());
    }

    @Test
    @DisplayName("Test setting and getting password")
    void testSetAndGetPassword() {
        // GIVEN: Initialize RedisProperty
        // WHEN: Set password to "mysecretpassword"
        redisProperty.setPassword("mysecretpassword");
        // THEN: Verify password is set correctly
        assertEquals("mysecretpassword", redisProperty.getPassword());
    }
}
