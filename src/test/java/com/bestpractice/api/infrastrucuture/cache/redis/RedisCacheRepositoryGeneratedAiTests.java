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

public class RedisCacheRepositoryGeneratedAiTests {

    private RedisCacheRepository repository;

    @BeforeEach
    void setUp() {
        // GIVEN: a new instance of RedisCacheRepository
        repository = new RedisCacheRepository();
    }

    @Test
    void shouldInstantiateRepository() {
        // GIVEN: repository is initialized in setUp()
        // WHEN: we reference the repository
        // THEN: the repository instance should not be null
        assertThat(repository).isNotNull();
    }

    @Test
    void shouldBeInstanceOfRedisCacheRepository() {
        // GIVEN: repository is initialized in setUp()
        // WHEN: we check its type
        // THEN: the repository should be an instance of RedisCacheRepository
        assertThat(repository).isInstanceOf(RedisCacheRepository.class);
    }
}
