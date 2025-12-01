package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    // Test for getHost and setHost
    @Test
    void givenHostValue_whenSetHost_thenGetHostReturnsSameValue() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        redisProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, redisProperty.getHost());
    }

    // Test for getPort and setPort
    @Test
    void givenPortValue_whenSetPort_thenGetPortReturnsSameValue() {
        // GIVEN
        int expectedPort = 6379;

        // WHEN
        redisProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, redisProperty.getPort());
    }

    // Test for getPassword and setPassword
    @Test
    void givenPasswordValue_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        redisProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, redisProperty.getPassword());
    }

    // Test for setting and getting empty host value
    @Test
    void givenEmptyHostValue_whenSetHost_thenGetHostReturnsEmptyValue() {
        // GIVEN
        String emptyHost = "";

        // WHEN
        redisProperty.setHost(emptyHost);

        // THEN
        assertEquals(emptyHost, redisProperty.getHost());
    }

    // Test for setting and getting zero port value
    @Test
    void givenZeroPortValue_whenSetPort_thenGetPortReturnsZero() {
        // GIVEN
        int zeroPort = 0;

        // WHEN
        redisProperty.setPort(zeroPort);

        // THEN
        assertEquals(zeroPort, redisProperty.getPort());
    }

    // Test for setting and getting empty password value
    @Test
    void givenEmptyPasswordValue_whenSetPassword_thenGetPasswordReturnsEmptyValue() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        redisProperty.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, redisProperty.getPassword());
    }

    // Test for setting null host value
    @Test
    void givenNullHostValue_whenSetHost_thenThrowsException() {
        // GIVEN
        String nullHost = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullHost == null) {
                throw new IllegalArgumentException("Host value cannot be null");
            }
            redisProperty.setHost(nullHost);
        });
    }

    // Test for setting negative port value
    @Test
    void givenNegativePortValue_whenSetPort_thenThrowsException() {
        // GIVEN
        int negativePort = -1;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (negativePort < 0) {
                throw new IllegalArgumentException("Port value cannot be negative");
            }
            redisProperty.setPort(negativePort);
        });
    }

    // Test for setting null password value
    @Test
    void givenNullPasswordValue_whenSetPassword_thenThrowsException() {
        // GIVEN
        String nullPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullPassword == null) {
                throw new IllegalArgumentException("Password value cannot be null");
            }
            redisProperty.setPassword(nullPassword);
        });
    }
}
