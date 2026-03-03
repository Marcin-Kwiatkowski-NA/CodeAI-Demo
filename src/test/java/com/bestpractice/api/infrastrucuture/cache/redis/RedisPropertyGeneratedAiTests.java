package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void testHostGetterAndSetter() {
        // GIVEN
        String expectedHost = "localhost";
        redisProperty.setHost(expectedHost);

        // WHEN
        String actualHost = redisProperty.getHost();

        // THEN
        assertEquals(expectedHost, actualHost);
    }

    @Test
    void testPortGetterAndSetter() {
        // GIVEN
        int expectedPort = 6379;
        redisProperty.setPort(expectedPort);

        // WHEN
        int actualPort = redisProperty.getPort();

        // THEN
        assertEquals(expectedPort, actualPort);
    }

    @Test
    void testPasswordGetterAndSetter() {
        // GIVEN
        String expectedPassword = "secret";
        redisProperty.setPassword(expectedPassword);

        // WHEN
        String actualPassword = redisProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testDefaultValues() {
        // GIVEN
        // No setup

        // WHEN
        String host = redisProperty.getHost();
        int port = redisProperty.getPort();
        String password = redisProperty.getPassword();

        // THEN
        assertEquals(null, host);
        assertEquals(0, port);
        assertEquals(null, password);
    }

    @Test
    void testSetNullValues() {
        // GIVEN
        // No setup

        // WHEN
        redisProperty.setHost(null);
        redisProperty.setPassword(null);
        redisProperty.setPort(0);

        // THEN
        assertEquals(null, redisProperty.getHost());
        assertEquals(0, redisProperty.getPort());
        assertEquals(null, redisProperty.getPassword());
    }
}
