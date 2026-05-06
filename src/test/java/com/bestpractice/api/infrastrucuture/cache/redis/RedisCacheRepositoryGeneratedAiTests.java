package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RedisCacheRepositoryGeneratedAiTests {

    private RedisCacheRepository redisCacheRepository;

    @BeforeEach
    void setUp() {
        redisCacheRepository = new RedisCacheRepository();
    }

    @Test
    void givenNewInstance_whenCreated_thenInstanceIsNotNull() {
        // GIVEN
        // RedisCacheRepository instance is created in setup

        // WHEN
        RedisCacheRepository instance = redisCacheRepository;

        // THEN
        assertNotNull(instance);
    }

    @Test
    void givenSameInstance_whenCompared_thenTheyAreEqual() {
        // GIVEN
        RedisCacheRepository instance1 = redisCacheRepository;

        // WHEN
        RedisCacheRepository instance2 = redisCacheRepository;

        // THEN
        assertEquals(instance1, instance2);
    }

    @Test
    void givenNewInstance_whenHashCodeCalled_thenReturnsConsistentValue() {
        // GIVEN
        RedisCacheRepository instance = redisCacheRepository;

        // WHEN
        int hashCode1 = instance.hashCode();
        int hashCode2 = instance.hashCode();

        // THEN
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenNewInstance_whenToStringCalled_thenReturnsNonNullString() {
        // GIVEN
        RedisCacheRepository instance = redisCacheRepository;

        // WHEN
        String result = instance.toString();

        // THEN
        assertNotNull(result);
    }

    @Test
    void givenNewInstance_whenMethodsCalled_thenNoExceptionThrown() {
        // GIVEN
        RedisCacheRepository instance = redisCacheRepository;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            instance.hashCode();
            instance.toString();
        });
    }
}
