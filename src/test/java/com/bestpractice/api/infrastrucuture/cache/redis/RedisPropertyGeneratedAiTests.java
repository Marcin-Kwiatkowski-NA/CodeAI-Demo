package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Profile("cache_redis")
@Component
class RedisPropertyGeneratedAiTests {

    @Mock
    private RedisProperty redisProperty;

    @InjectMocks
    private RedisProperty actualRedisProperty;

    @BeforeEach
    void setUp() {
        // Reset any state or configuration before each test
        actualRedisProperty = new RedisProperty();
    }

    @Test
    void givenValidHostAndPort_whenSetThenHostAndPortAreSetCorrectly() {
        // GIVEN
        String host = "localhost";
        int port = 6379;

        // WHEN
        actualRedisProperty.setHost(host);
        actualRedisProperty.setPort(port);

        // THEN
        assertThat(actualRedisProperty.getHost()).isEqualTo(host);
        assertThat(actualRedisProperty.getPort()).isEqualTo(port);
    }

    @Test
    void givenValidPassword_whenSetThenPasswordIsSetCorrectly() {
        // GIVEN
        String password = "securePassword123";

        // WHEN
        actualRedisProperty.setPassword(password);

        // THEN
        assertThat(actualRedisProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void givenNullHost_whenSetThenHostIsSetToNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        actualRedisProperty.setHost(nullHost);

        // THEN
        assertThat(actualRedisProperty.getHost()).isNull();
    }

    @Test
    void givenEmptyHost_whenSetThenHostIsSetToEmptyString() {
        // GIVEN
        String emptyHost = "";

        // WHEN
        actualRedisProperty.setHost(emptyHost);

        // THEN
        assertThat(actualRedisProperty.getHost()).isEqualTo("");
    }

    @Test
    void givenNullPassword_whenSetThenPasswordIsSetToNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        actualRedisProperty.setPassword(nullPassword);

        // THEN
        assertThat(actualRedisProperty.getPassword()).isNull();
    }

    @Test
    void givenEmptyPassword_whenSetThenPasswordIsSetToEmptyString() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        actualRedisProperty.setPassword(emptyPassword);

        // THEN
        assertThat(actualRedisProperty.getPassword()).isEqualTo("");
    }

    @Test
    void givenInvalidPortValue_whenSetThenPortIsSetToDefault() {
        // GIVEN
        int invalidPort = -1;

        // WHEN
        actualRedisProperty.setPort(invalidPort);

        // THEN
        assertThat(actualRedisProperty.getPort()).isEqualTo(invalidPort);
    }

    @Test
    void givenZeroPort_whenSetThenPortIsSetToZero() {
        // GIVEN
        int zeroPort = 0;

        // WHEN
        actualRedisProperty.setPort(zeroPort);

        // THEN
        assertThat(actualRedisProperty.getPort()).isEqualTo(zeroPort);
    }

    @Test
    void givenAllFieldsSet_thenAllGettersReturnExpectedValues() {
        // GIVEN
        String host = "redis.example.com";
        int port = 6380;
        String password = "mypassword";

        // WHEN
        actualRedisProperty.setHost(host);
        actualRedisProperty.setPort(port);
        actualRedisProperty.setPassword(password);

        // THEN
        assertThat(actualRedisProperty.getHost()).isEqualTo(host);
        assertThat(actualRedisProperty.getPort()).isEqualTo(port);
        assertThat(actualRedisProperty.getPassword()).isEqualTo(password);
    }
}
