package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        String expectedPassword = "securePassword"; // security-sensitive placeholder

        // WHEN
        redisProperty.setPassword(expectedPassword);
        String actualPassword = redisProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testDefaultValuesAreNullOrZero() {
        // GIVEN
        // No setup required

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
    void testSetHostWithNullValue() {
        // GIVEN
        String expectedHost = null;

        // WHEN
        redisProperty.setHost(expectedHost);
        String actualHost = redisProperty.getHost();

        // THEN
        assertNull(actualHost);
    }

    @Test
    void testSetPasswordWithNullValue() {
        // GIVEN
        String expectedPassword = null;

        // WHEN
        redisProperty.setPassword(expectedPassword);
        String actualPassword = redisProperty.getPassword();

        // THEN
        assertNull(actualPassword);
    }

    @Test
    void testSetPortWithNegativeValue() {
        // GIVEN
        int negativePort = -1;

        // WHEN
        redisProperty.setPort(negativePort);
        int actualPort = redisProperty.getPort();

        // THEN
        assertEquals(negativePort, actualPort);
    }

    @Test
    void testNoExceptionThrownForValidValues() {
        // GIVEN
        String host = "127.0.0.1";
        int port = 6379;
        String password = "securePassword";

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            redisProperty.setHost(host);
            redisProperty.setPort(port);
            redisProperty.setPassword(password);
        });

        assertEquals(host, redisProperty.getHost());
        assertEquals(port, redisProperty.getPort());
        assertEquals(password, redisProperty.getPassword());
    }

    @Test
    void testNoExceptionThrownForNullValues() {
        // GIVEN
        String host = null;
        String password = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            redisProperty.setHost(host);
            redisProperty.setPassword(password);
        });

        assertNull(redisProperty.getHost());
        assertNull(redisProperty.getPassword());
    }
}
