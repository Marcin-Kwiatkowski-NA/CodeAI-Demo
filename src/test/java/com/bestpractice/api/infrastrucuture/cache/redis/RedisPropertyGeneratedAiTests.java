package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void testSetAndGetHost() {
        String expectedHost = "localhost";
        redisProperty.setHost(expectedHost);
        String actualHost = redisProperty.getHost();
        assertEquals(expectedHost, actualHost);
    }

    @Test
    void testSetAndGetPort() {
        int expectedPort = 6379;
        redisProperty.setPort(expectedPort);
        int actualPort = redisProperty.getPort();
        assertEquals(expectedPort, actualPort);
    }

    @Test
    void testSetAndGetPassword() {
        String expectedPassword = "securePassword";
        redisProperty.setPassword(expectedPassword);
        String actualPassword = redisProperty.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testDefaultValuesBeforeSetters() {
        String host = redisProperty.getHost();
        int port = redisProperty.getPort();
        String password = redisProperty.getPassword();
        assertEquals(null, host);
        assertEquals(0, port);
        assertEquals(null, password);
    }

    @Test
    void testSetHostWithNullValueDoesNotThrowException() {
        String nullHost = null;
        assertDoesNotThrow(() -> redisProperty.setHost(nullHost));
        assertEquals(null, redisProperty.getHost());
    }

    @Test
    void testSetPasswordWithNullValueDoesNotThrowException() {
        String nullPassword = null;
        assertDoesNotThrow(() -> redisProperty.setPassword(nullPassword));
        assertEquals(null, redisProperty.getPassword());
    }

    @Test
    void testSetPortWithNegativeValueDoesNotThrowException() {
        int negativePort = -1;
        assertDoesNotThrow(() -> redisProperty.setPort(negativePort));
        assertEquals(negativePort, redisProperty.getPort());
    }

    @Test
    void testSetHostWithEmptyString() {
        String emptyHost = "";
        redisProperty.setHost(emptyHost);
        String result = redisProperty.getHost();
        assertEquals(emptyHost, result);
    }

    @Test
    void testSetHostWithWhitespaceString() {
        String whitespaceHost = "   ";
        redisProperty.setHost(whitespaceHost);
        String result = redisProperty.getHost();
        assertEquals(whitespaceHost, result);
    }

    @Test
    void testSetPasswordWithEmptyString() {
        String emptyPassword = "";
        redisProperty.setPassword(emptyPassword);
        String result = redisProperty.getPassword();
        assertEquals(emptyPassword, result);
    }

    @Test
    void testSetPasswordWithWhitespaceString() {
        String whitespacePassword = "   ";
        redisProperty.setPassword(whitespacePassword);
        String result = redisProperty.getPassword();
        assertEquals(whitespacePassword, result);
    }

    @Test
    void testSetPortWithZero() {
        int zeroPort = 0;
        redisProperty.setPort(zeroPort);
        int result = redisProperty.getPort();
        assertEquals(zeroPort, result);
    }

    @Test
    void testSetPortWithOne() {
        int onePort = 1;
        redisProperty.setPort(onePort);
        int result = redisProperty.getPort();
        assertEquals(onePort, result);
    }

    @Test
    void testSetPortWithIntegerMaxValue() {
        int maxPort = Integer.MAX_VALUE;
        redisProperty.setPort(maxPort);
        int result = redisProperty.getPort();
        assertEquals(maxPort, result);
    }

    @Test
    void testSetPortWithIntegerMinValue() {
        int minPort = Integer.MIN_VALUE;
        redisProperty.setPort(minPort);
        int result = redisProperty.getPort();
        assertEquals(minPort, result);
    }

    @Test
    void testMultiplePropertyAssignments() {
        String host = "127.0.0.1";
        int port = 8080;
        String password = "pass123";
        redisProperty.setHost(host);
        redisProperty.setPort(port);
        redisProperty.setPassword(password);
        assertEquals(host, redisProperty.getHost());
        assertEquals(port, redisProperty.getPort());
        assertEquals(password, redisProperty.getPassword());
    }

    @Test
    void testReassigningValuesOverwritesPreviousOnes() {
        redisProperty.setHost("oldHost");
        redisProperty.setPort(1000);
        redisProperty.setPassword("oldPass");
        redisProperty.setHost("newHost");
        redisProperty.setPort(2000);
        redisProperty.setPassword("newPass");
        assertEquals("newHost", redisProperty.getHost());
        assertEquals(2000, redisProperty.getPort());
        assertEquals("newPass", redisProperty.getPassword());
    }
}
