package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RedisCacheRepositoryGeneratedAiTests {

    @BeforeEach
    void resetState() {
        // No state to reset for this class
    }

    @Test
    void constructorCreatesInstance() {
        // GIVEN: no preconditions

        // WHEN: create instance
        RedisCacheRepository repository = new RedisCacheRepository();

        // THEN: repository is not null
        assertNotNull(repository);
    }

    @Test
    void constructorDoesNotThrowException() {
        // GIVEN: no preconditions

        // WHEN: create instance
        // THEN: no exception should be thrown
        assertDoesNotThrow(() -> new RedisCacheRepository());
    }

    @Test
    void multipleInstancesAreDistinct() {
        // GIVEN: no preconditions

        // WHEN: create two separate instances
        RedisCacheRepository repo1 = new RedisCacheRepository();
        RedisCacheRepository repo2 = new RedisCacheRepository();

        // THEN: the two instances should not be the same object
        assertNotEquals(repo1, repo2);
    }
}
