package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocalCacheRepositoryGeneratedAiTests {

    private LocalCacheRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalCacheRepository();
    }

    // GIVEN: A new LocalCacheRepository instance is created.
    // WHEN: The get method is called without any arguments.
    // THEN: The method should return null.
    @Test
    void testGet_returnsNull() {
        assertNull(repository.get());
    }

    // GIVEN: A key is provided to the get method.
    // WHEN: The get method is called with the key.
    // THEN: The method should return null.
    @Test
    void testGet_withKey_returnsNull() {
        String key = "testKey";
        assertNull(repository.get(key));
    }

    // GIVEN: A key is provided to the put method.
    // WHEN: The put method is called with the key and a value.
    // THEN: The method should not throw an exception.
    @Test
    void testPut_doesNotThrowException() {
        String key = "testKey";
        String value = "testValue";
        repository.put(key, value);
        assertNotNull(repository.get(key));
    }

    // GIVEN: A key is provided to the remove method.
    // WHEN: The remove method is called with the key.
    // THEN: The method should not throw an exception.
    @Test
    void testRemove_doesNotThrowException() {
        String key = "testKey";
        String value = "testValue";
        repository.put(key, value);
        repository.remove(key);
        assertNull(repository.get(key));
    }
}
