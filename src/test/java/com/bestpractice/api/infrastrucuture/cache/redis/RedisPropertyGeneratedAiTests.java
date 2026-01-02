package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    void testSetAndGetHost() {
        // GIVEN
        String host = "localhost";
        // WHEN
        redisProperty.setHost(host);
        // THEN
        assertThat(redisProperty.getHost()).isEqualTo(host);
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN
        int port = 6379;
        // WHEN
        redisProperty.setPort(port);
        // THEN
        assertThat(redisProperty.getPort()).isEqualTo(port);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String password = "secret";
        // WHEN
        redisProperty.setPassword(password);
        // THEN
        assertThat(redisProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void testSetMultipleProperties() {
        // GIVEN
        String host = "redis.example.com";
        int port = 6380;
        String password = "mypassword";
        // WHEN
        redisProperty.setHost(host);
        redisProperty.setPort(port);
        redisProperty.setPassword(password);
        // THEN
        assertThat(redisProperty.getHost()).isEqualTo(host);
        assertThat(redisProperty.getPort()).isEqualTo(port);
        assertThat(redisProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void testOverrideProperties() {
        // GIVEN
        redisProperty.setHost("initial");
        redisProperty.setPort(1234);
        redisProperty.setPassword("init");
        // WHEN
        redisProperty.setHost("newHost");
        redisProperty.setPort(5678);
        redisProperty.setPassword("newPass");
        // THEN
        assertThat(redisProperty.getHost()).isEqualTo("newHost");
        assertThat(redisProperty.getPort()).isEqualTo(5678);
        assertThat(redisProperty.getPassword()).isEqualTo("newPass");
    }

    @Test
    void testNegativePortValue() {
        // GIVEN
        int negativePort = -1;
        // WHEN
        redisProperty.setPort(negativePort);
        // THEN
        assertThat(redisProperty.getPort()).isEqualTo(negativePort);
    }

    @Test
    void testNullHostAndPassword() {
        // GIVEN
        // WHEN
        redisProperty.setHost(null);
        redisProperty.setPassword(null);
        // THEN
        assertThat(redisProperty.getHost()).isNull();
        assertThat(redisProperty.getPassword()).isNull();
    }

    @Test
    void testEmptyHostString() {
        // GIVEN
        String emptyHost = "";
        // WHEN
        redisProperty.setHost(emptyHost);
        // THEN
        assertThat(redisProperty.getHost()).isEqualTo(emptyHost);
    }

    @Test
    void testLargePortNumber() {
        // GIVEN
        int largePort = 65535;
        // WHEN
        redisProperty.setPort(largePort);
        // THEN
        assertThat(redisProperty.getPort()).isEqualTo(largePort);
    }
}
