package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
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
    void testDefaultValues() {
        // GIVEN a new RedisProperty instance
        // WHEN no setters are called
        // THEN the default values should be null for host and password, and 0 for port
        assertThat(redisProperty.getHost()).isNull();
        assertThat(redisProperty.getPassword()).isNull();
        assertThat(redisProperty.getPort()).isZero();
    }

    @Test
    void testSetHost() {
        // GIVEN a RedisProperty instance
        // WHEN setting the host to "localhost"
        // THEN getHost should return "localhost"
        redisProperty.setHost("localhost");
        assertThat(redisProperty.getHost()).isEqualTo("localhost");
    }

    @Test
    void testSetPort() {
        // GIVEN a RedisProperty instance
        // WHEN setting the port to 6379
        // THEN getPort should return 6379
        redisProperty.setPort(6379);
        assertThat(redisProperty.getPort()).isEqualTo(6379);
    }

    @Test
    void testSetPassword() {
        // GIVEN a RedisProperty instance
        // WHEN setting the password to "secret"
        // THEN getPassword should return "secret"
        redisProperty.setPassword("secret");
        assertThat(redisProperty.getPassword()).isEqualTo("secret");
    }

    @Test
    void testSetNullHost() {
        // GIVEN a RedisProperty instance
        // WHEN setting the host to null
        // THEN getHost should return null
        redisProperty.setHost(null);
        assertThat(redisProperty.getHost()).isNull();
    }

    @Test
    void testSetNegativePort() {
        // GIVEN a RedisProperty instance
        // WHEN setting the port to a negative value
        // THEN getPort should return that negative value
        redisProperty.setPort(-1);
        assertThat(redisProperty.getPort()).isEqualTo(-1);
    }

    @Test
    void testSetMultipleValues() {
        // GIVEN a RedisProperty instance
        // WHEN setting host, port, and password
        // THEN each getter should return the corresponding value
        redisProperty.setHost("redis.example.com");
        redisProperty.setPort(6380);
        redisProperty.setPassword("mypassword");
        assertThat(redisProperty.getHost()).isEqualTo("redis.example.com");
        assertThat(redisProperty.getPort()).isEqualTo(6380);
        assertThat(redisProperty.getPassword()).isEqualTo("mypassword");
    }

    @Test
    void testUpdateValues() {
        // GIVEN a RedisProperty instance with initial values
        redisProperty.setHost("initial");
        redisProperty.setPort(1234);
        redisProperty.setPassword("initpass");
        // WHEN updating the values
        redisProperty.setHost("updated");
        redisProperty.setPort(5678);
        redisProperty.setPassword("updatedpass");
        // THEN getters should reflect the updated values
        assertThat(redisProperty.getHost()).isEqualTo("updated");
        assertThat(redisProperty.getPort()).isEqualTo(5678);
        assertThat(redisProperty.getPassword()).isEqualTo("updatedpass");
    }

    @Test
    void testSetNullPassword() {
        // GIVEN a RedisProperty instance
        // WHEN setting the password to null
        // THEN getPassword should return null
        redisProperty.setPassword(null);
        assertThat(redisProperty.getPassword()).isNull();
    }

    @Test
    void testSetEmptyHost() {
        // GIVEN a RedisProperty instance
        // WHEN setting the host to an empty string
        // THEN getHost should return an empty string
        redisProperty.setHost("");
        assertThat(redisProperty.getHost()).isEqualTo("");
    }

    @Test
    void testSetEmptyPassword() {
        // GIVEN a RedisProperty instance
        // WHEN setting the password to an empty string
        // THEN getPassword should return an empty string
        redisProperty.setPassword("");
        assertThat(redisProperty.getPassword()).isEqualTo("");
    }

    @Test
    void testPortBoundaries() {
        // GIVEN a RedisProperty instance
        // WHEN setting the port to Integer.MAX_VALUE
        // THEN getPort should return Integer.MAX_VALUE
        redisProperty.setPort(Integer.MAX_VALUE);
        assertThat(redisProperty.getPort()).isEqualTo(Integer.MAX_VALUE);

        // WHEN setting the port to Integer.MIN_VALUE
        // THEN getPort should return Integer.MIN_VALUE
        redisProperty.setPort(Integer.MIN_VALUE);
        assertThat(redisProperty.getPort()).isEqualTo(Integer.MIN_VALUE);
    }

    @Test
    void testPortZero() {
        // GIVEN a RedisProperty instance
        // WHEN setting the port to 0
        // THEN getPort should return 0
        redisProperty.setPort(0);
        assertThat(redisProperty.getPort()).isZero();
    }
}
