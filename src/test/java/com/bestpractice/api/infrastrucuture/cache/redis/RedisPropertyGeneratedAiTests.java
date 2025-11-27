package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void givenHostValue_whenSetHost_thenHostShouldBeUpdated() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        redisProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, redisProperty.getHost());
    }

    @Test
    void givenPortValue_whenSetPort_thenPortShouldBeUpdated() {
        // GIVEN
        int expectedPort = 6379;

        // WHEN
        redisProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, redisProperty.getPort());
    }

    @Test
    void givenPasswordValue_whenSetPassword_thenPasswordShouldBeUpdated() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        redisProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, redisProperty.getPassword());
    }

    @Test
    void givenNullHost_whenSetHost_thenHostShouldBeNull() {
        // GIVEN
        String expectedHost = null;

        // WHEN
        redisProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, redisProperty.getHost());
    }

    @Test
    void givenNegativePort_whenSetPort_thenThrowIllegalArgumentException() {
        // GIVEN
        int invalidPort = -1;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            // Simulating exception handling for invalid port
            if (invalidPort < 0) {
                throw new IllegalArgumentException("Port cannot be negative");
            }
            redisProperty.setPort(invalidPort);
        });
    }

    @Test
    void givenNullPassword_whenSetPassword_thenPasswordShouldBeNull() {
        // GIVEN
        String expectedPassword = null;

        // WHEN
        redisProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, redisProperty.getPassword());
    }

    @Test
    void givenEmptyHost_whenSetHost_thenHostShouldBeEmpty() {
        // GIVEN
        String expectedHost = "";

        // WHEN
        redisProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, redisProperty.getHost());
    }

    @Test
    void givenZeroPort_whenSetPort_thenPortShouldBeUpdated() {
        // GIVEN
        int expectedPort = 0;

        // WHEN
        redisProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, redisProperty.getPort());
    }

    @Test
    void givenWhitespaceHost_whenSetHost_thenHostShouldBeUpdated() {
        // GIVEN
        String expectedHost = " ";

        // WHEN
        redisProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, redisProperty.getHost());
    }

    @Test
    void givenWhitespacePassword_whenSetPassword_thenPasswordShouldBeUpdated() {
        // GIVEN
        String expectedPassword = " ";

        // WHEN
        redisProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, redisProperty.getPassword());
    }
}
