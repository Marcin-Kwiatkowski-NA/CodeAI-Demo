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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class RedisCacheRepositoryGeneratedAiTests {

    private RedisCacheRepository repository;

    @BeforeEach
    void setUp() {
        // GIVEN: a fresh instance of the repository
        repository = new RedisCacheRepository();
    }

    @Test
    void testInstanceCreation() {
        // GIVEN: repository instance created in setUp
        // WHEN: repository is instantiated
        // THEN: the instance should not be null
        assertThat(repository).isNotNull();
    }

    @Test
    void testNoPublicMethods() {
        // GIVEN: repository instance
        // WHEN: retrieving all declared methods of the class
        Method[] methods = RedisCacheRepository.class.getDeclaredMethods();
        // THEN: there should be no public methods other than the constructor
        assertThat(methods).filteredOn(method -> Modifier.isPublic(method.getModifiers()))
                .isEmpty();
    }

    @Test
    void testNoProtectedMethods() {
        // GIVEN: repository instance
        // WHEN: retrieving all declared methods of the class
        Method[] methods = RedisCacheRepository.class.getDeclaredMethods();
        // THEN: there should be no protected methods
        assertThat(methods).filteredOn(method -> Modifier.isProtected(method.getModifiers()))
                .isEmpty();
    }
}
