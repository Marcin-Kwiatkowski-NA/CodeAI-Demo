package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        redisProperty.setHost(expectedHost);
        String actualHost = redisProperty.getHost();

        // THEN
        assertEquals(expectedHost, actualHost);
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN
        int expectedPort = 6379;

        // WHEN
        redisProperty.setPort(expectedPort);
        int actualPort = redisProperty.getPort();

        // THEN
        assertEquals(expectedPort, actualPort);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword"; // security-sensitive

        // WHEN
        redisProperty.setPassword(expectedPassword);
        String actualPassword = redisProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testDefaultValues() {
        // GIVEN
        // No values set

        // WHEN
        String host = redisProperty.getHost();
        int port = redisProperty.getPort();
        String password = redisProperty.getPassword();

        // THEN
        assertNull(host);
        assertEquals(0, port);
        assertNull(password);
    }

    @Test
    void testSetHostNullValue() {
        // GIVEN
        String nullHost = null;

        // WHEN
        redisProperty.setHost(nullHost);

        // THEN
        assertNull(redisProperty.getHost());
    }

    @Test
    void testSetPasswordNullValue() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        redisProperty.setPassword(nullPassword);

        // THEN
        assertNull(redisProperty.getPassword());
    }

    @Test
    void testSetPortNegativeValue() {
        // GIVEN
        int negativePort = -100;

        // WHEN
        redisProperty.setPort(negativePort);

        // THEN
        assertEquals(negativePort, redisProperty.getPort());
    }
}
