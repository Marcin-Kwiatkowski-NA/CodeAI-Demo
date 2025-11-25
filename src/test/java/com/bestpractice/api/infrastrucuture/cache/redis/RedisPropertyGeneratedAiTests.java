package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
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
    void givenHostValue_whenSetHost_thenHostShouldBeUpdated() {
        // GIVEN
        String host = "localhost";

        // WHEN
        redisProperty.setHost(host);

        // THEN
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void givenPortValue_whenSetPort_thenPortShouldBeUpdated() {
        // GIVEN
        int port = 6379;

        // WHEN
        redisProperty.setPort(port);

        // THEN
        assertEquals(6379, redisProperty.getPort());
    }

    @Test
    void givenPasswordValue_whenSetPassword_thenPasswordShouldBeUpdated() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        redisProperty.setPassword(password);

        // THEN
        assertEquals("securePassword", redisProperty.getPassword());
    }

    @Test
    void givenNullHost_whenSetHost_thenHostShouldBeNull() {
        // GIVEN
        String host = null;

        // WHEN
        redisProperty.setHost(host);

        // THEN
        assertEquals(null, redisProperty.getHost());
    }

    @Test
    void givenNegativePort_whenSetPort_thenPortShouldBeUpdated() {
        // GIVEN
        int port = -1;

        // WHEN
        redisProperty.setPort(port);

        // THEN
        assertEquals(-1, redisProperty.getPort());
    }

    @Test
    void givenNullPassword_whenSetPassword_thenPasswordShouldBeNull() {
        // GIVEN
        String password = null;

        // WHEN
        redisProperty.setPassword(password);

        // THEN
        assertEquals(null, redisProperty.getPassword());
    }
}
