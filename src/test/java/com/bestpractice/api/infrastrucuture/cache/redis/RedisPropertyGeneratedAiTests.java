package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    public void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    public void testSetAndGetHost() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        redisProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, redisProperty.getHost());
    }

    @Test
    public void testSetAndGetPort() {
        // GIVEN
        int expectedPort = 6379;

        // WHEN
        redisProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, redisProperty.getPort());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "password123";

        // WHEN
        redisProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, redisProperty.getPassword());
    }
}
