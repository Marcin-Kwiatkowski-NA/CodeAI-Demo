package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a RedisProperty instance and a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        redisProperty.setHost(expectedHost);

        // THEN: the retrieved host should match the expected value
        assertEquals(expectedHost, redisProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a RedisProperty instance and a port value
        int expectedPort = 6379;

        // WHEN: setting the port
        redisProperty.setPort(expectedPort);

        // THEN: the retrieved port should match the expected value
        assertEquals(expectedPort, redisProperty.getPort());
    }

    @Test
    void testSetAndGetPassword() {
        // SECURITY-SENSITIVE: password handling
        // GIVEN: a RedisProperty instance and a password value
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        redisProperty.setPassword(expectedPassword);

        // THEN: the retrieved password should match the expected value
        assertEquals(expectedPassword, redisProperty.getPassword());
    }

    @Test
    void testDefaultValues() {
        // GIVEN: a new RedisProperty instance

        // WHEN: retrieving default values without setting them

        // THEN: defaults should be null for host and password, and 0 for port
        assertNull(redisProperty.getHost());
        assertNull(redisProperty.getPassword());
        assertEquals(0, redisProperty.getPort());
    }
}
