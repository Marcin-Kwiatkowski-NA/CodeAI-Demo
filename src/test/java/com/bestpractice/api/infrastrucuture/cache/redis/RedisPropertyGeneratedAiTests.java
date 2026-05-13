package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void shouldSetAndGetHostCorrectly() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        redisProperty.setHost(expectedHost);
        String actualHost = redisProperty.getHost();

        // THEN
        assertEquals(expectedHost, actualHost);
    }

    @Test
    void shouldSetAndGetPortCorrectly() {
        // GIVEN
        int expectedPort = 6379;

        // WHEN
        redisProperty.setPort(expectedPort);
        int actualPort = redisProperty.getPort();

        // THEN
        assertEquals(expectedPort, actualPort);
    }

    @Test
    void shouldSetAndGetPasswordCorrectly() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        redisProperty.setPassword(expectedPassword);
        String actualPassword = redisProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void shouldHandleNullHostGracefully() {
        // GIVEN
        String expectedHost = null;

        // WHEN
        redisProperty.setHost(expectedHost);
        String actualHost = redisProperty.getHost();

        // THEN
        assertEquals(expectedHost, actualHost);
    }

    @Test
    void shouldHandleNegativePortValueGracefully() {
        // GIVEN
        int invalidPort = -1;

        // WHEN
        redisProperty.setPort(invalidPort);
        int actualPort = redisProperty.getPort();

        // THEN
        assertEquals(invalidPort, actualPort);
    }

    @Test
    void shouldHandleEmptyPasswordGracefully() {
        // GIVEN
        String expectedPassword = "";

        // WHEN
        redisProperty.setPassword(expectedPassword);
        String actualPassword = redisProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void shouldNotThrowExceptionWhenSettingValidValues() {
        // GIVEN
        String host = "127.0.0.1";
        int port = 6379;
        String password = "safePass";

        // WHEN
        redisProperty.setHost(host);
        redisProperty.setPort(port);
        redisProperty.setPassword(password);

        // THEN
        assertEquals(host, redisProperty.getHost());
        assertEquals(port, redisProperty.getPort());
        assertEquals(password, redisProperty.getPassword());
    }

    @Test
    void shouldNotThrowExceptionWhenSettingNullValues() {
        // GIVEN
        String host = null;
        String password = null;

        // WHEN
        redisProperty.setHost(host);
        redisProperty.setPassword(password);

        // THEN
        assertEquals(host, redisProperty.getHost());
        assertEquals(password, redisProperty.getPassword());
    }

    @Test
    void shouldThrowExceptionWhenAccessingUninitializedPropertyIfApplicable() {
        // GIVEN
        RedisProperty uninitializedProperty = new RedisProperty();

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            String host = uninitializedProperty.getHost().toString();
        });
    }
}
