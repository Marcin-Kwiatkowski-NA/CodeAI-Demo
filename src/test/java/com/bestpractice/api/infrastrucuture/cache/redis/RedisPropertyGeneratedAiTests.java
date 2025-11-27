package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
    void givenHostValue_whenSetHost_thenGetHostReturnsSameValue() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        redisProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, redisProperty.getHost());
    }

    @Test
    void givenPortValue_whenSetPort_thenGetPortReturnsSameValue() {
        // GIVEN
        int expectedPort = 6379;

        // WHEN
        redisProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, redisProperty.getPort());
    }

    @Test
    void givenPasswordValue_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        redisProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, redisProperty.getPassword());
    }

    @Test
    void givenNullHostValue_whenSetHost_thenGetHostReturnsNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        redisProperty.setHost(nullHost);

        // THEN
        assertEquals(nullHost, redisProperty.getHost());
    }

    @Test
    void givenNegativePortValue_whenSetPort_thenGetPortReturnsNegativeValue() {
        // GIVEN
        int negativePort = -1;

        // WHEN
        redisProperty.setPort(negativePort);

        // THEN
        assertEquals(negativePort, redisProperty.getPort());
    }

    @Test
    void givenNullPasswordValue_whenSetPassword_thenGetPasswordReturnsNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        redisProperty.setPassword(nullPassword);

        // THEN
        assertEquals(nullPassword, redisProperty.getPassword());
    }

    @Test
    void givenEmptyHostValue_whenSetHost_thenGetHostReturnsEmptyString() {
        // GIVEN
        String emptyHost = "";

        // WHEN
        redisProperty.setHost(emptyHost);

        // THEN
        assertEquals(emptyHost, redisProperty.getHost());
    }

    @Test
    void givenZeroPortValue_whenSetPort_thenGetPortReturnsZero() {
        // GIVEN
        int zeroPort = 0;

        // WHEN
        redisProperty.setPort(zeroPort);

        // THEN
        assertEquals(zeroPort, redisProperty.getPort());
    }

    @Test
    void givenEmptyPasswordValue_whenSetPassword_thenGetPasswordReturnsEmptyString() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        redisProperty.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, redisProperty.getPassword());
    }

    @Test
    void givenValidHostValue_whenSetHost_thenGetHostReturnsSameValue() {
        // GIVEN
        String validHost = "127.0.0.1";

        // WHEN
        redisProperty.setHost(validHost);

        // THEN
        assertEquals(validHost, redisProperty.getHost());
    }

    @Test
    void givenValidPortValue_whenSetPort_thenGetPortReturnsSameValue() {
        // GIVEN
        int validPort = 8080;

        // WHEN
        redisProperty.setPort(validPort);

        // THEN
        assertEquals(validPort, redisProperty.getPort());
    }

    @Test
    void givenValidPasswordValue_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String validPassword = "password123";

        // WHEN
        redisProperty.setPassword(validPassword);

        // THEN
        assertEquals(validPassword, redisProperty.getPassword());
    }
}
