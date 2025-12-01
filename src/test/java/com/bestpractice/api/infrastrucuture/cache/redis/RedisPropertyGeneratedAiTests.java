package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(redisProperty.getHost()).isEqualTo(expectedHost);
    }

    @Test
    void givenPortValue_whenSetPort_thenPortShouldBeUpdated() {
        // GIVEN
        int expectedPort = 6379;

        // WHEN
        redisProperty.setPort(expectedPort);

        // THEN
        assertThat(redisProperty.getPort()).isEqualTo(expectedPort);
    }

    @Test
    void givenPasswordValue_whenSetPassword_thenPasswordShouldBeUpdated() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        redisProperty.setPassword(expectedPassword);

        // THEN
        assertThat(redisProperty.getPassword()).isEqualTo(expectedPassword);
    }
}
