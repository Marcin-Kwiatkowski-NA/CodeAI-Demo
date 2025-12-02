package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void givenNullHostValue_whenSetHost_thenHostShouldBeNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        redisProperty.setHost(nullHost);

        // THEN
        assertEquals(nullHost, redisProperty.getHost());
    }

    @Test
    void givenNegativePortValue_whenSetPort_thenPortShouldBeUpdated() {
        // GIVEN
        int negativePort = -1;

        // WHEN
        redisProperty.setPort(negativePort);

        // THEN
        assertEquals(negativePort, redisProperty.getPort());
    }

    @Test
    void givenNullPasswordValue_whenSetPassword_thenPasswordShouldBeNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        redisProperty.setPassword(nullPassword);

        // THEN
        assertEquals(nullPassword, redisProperty.getPassword());
    }

    @Test
    void givenEmptyHostValue_whenSetHost_thenHostShouldBeUpdated() {
        // GIVEN
        String emptyHost = "";

        // WHEN
        redisProperty.setHost(emptyHost);

        // THEN
        assertEquals(emptyHost, redisProperty.getHost());
    }

    @Test
    void givenZeroPortValue_whenSetPort_thenPortShouldBeUpdated() {
        // GIVEN
        int zeroPort = 0;

        // WHEN
        redisProperty.setPort(zeroPort);

        // THEN
        assertEquals(zeroPort, redisProperty.getPort());
    }

    @Test
    void givenEmptyPasswordValue_whenSetPassword_thenPasswordShouldBeUpdated() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        redisProperty.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, redisProperty.getPassword());
    }
}
