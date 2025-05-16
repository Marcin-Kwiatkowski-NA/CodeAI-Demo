package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@DisplayNameTestExtension
class LocalCacheRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    void constructorTest() {
        // GIVEN: No preconditions
        // WHEN: Constructor is called
        // THEN: The constructor should execute without throwing exceptions.
        LocalCacheRepository repository = new LocalCacheRepository();
        assertNotNull(repository);
    }

    @Test
    void getCacheNameTest() {
        // GIVEN: A LocalCacheRepository instance
        // WHEN: The getCacheName method is called
        // THEN: The method should return the cache name.
        LocalCacheRepository repository = new LocalCacheRepository();
        String cacheName = repository.getCacheName();
        assertEquals("local", cacheName);
    }

    @Test
    void setCacheNameTest() {
        // GIVEN: A LocalCacheRepository instance
        // WHEN: The setCacheName method is called with a new cache name
        // THEN: The cache name should be updated.
        LocalCacheRepository repository = new LocalCacheRepository();
        repository.setCacheName("newLocal");
        assertEquals("newLocal", repository.getCacheName());
    }
}
