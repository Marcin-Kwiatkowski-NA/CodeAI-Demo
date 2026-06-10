package com.bestpractice.api.infrastrucuture.cache;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No input parameters
        // WHEN: Creating a new CacheRepository instance using the default constructor
        CacheRepository repository = new CacheRepository();
        // THEN: The repository should be created successfully
        assertThat(repository).isNotNull();
    }

    @Test
    void testPutAndGetSingleElement() {
        // GIVEN: A CacheRepository instance and a single key-value pair
        CacheRepository repository = new CacheRepository();
        String key = "testKey";
        String value = "testValue";
        // WHEN: Putting and retrieving the value
        repository.put(key, value);
        Object result = repository.get(key);
        // THEN: The retrieved value should match the stored value
        assertEquals(value, result);
    }

    @Test
    void testPutAndGetEmptyKey() {
        // GIVEN: A CacheRepository instance and an empty key
        CacheRepository repository = new CacheRepository();
        String key = "";
        String value = "emptyKeyValue";
        // WHEN: Putting and retrieving the value
        repository.put(key, value);
        Object result = repository.get(key);
        // THEN: The retrieved value should match the stored value
        assertEquals(value, result);
    }

    @Test
    void testPutAndGetWhitespaceKey() {
        // GIVEN: A CacheRepository instance and a whitespace key
        CacheRepository repository = new CacheRepository();
        String key = "   ";
        String value = "whitespaceValue";
        // WHEN: Putting and retrieving the value
        repository.put(key, value);
        Object result = repository.get(key);
        // THEN: The retrieved value should match the stored value
        assertEquals(value, result);
    }

    @Test
    void testPutAndGetNullKey() {
        // GIVEN: A CacheRepository instance and a null key
        CacheRepository repository = new CacheRepository();
        String key = null;
        String value = "nullKeyValue";
        // WHEN: Putting and retrieving the value
        repository.put(key, value);
        Object result = repository.get(key);
        // THEN: The retrieved value should match the stored value
        assertEquals(value, result);
    }

    @Test
    void testPutAndGetNullValue() {
        // GIVEN: A CacheRepository instance and a null value
        CacheRepository repository = new CacheRepository();
        String key = "nullValueKey";
        Object value = null;
        // WHEN: Putting and retrieving the value
        repository.put(key, value);
        Object result = repository.get(key);
        // THEN: The retrieved value should be null
        assertEquals(null, result);
    }

    @Test
    void testOverwriteExistingKey() {
        // GIVEN: A CacheRepository instance and a key with two different values
        CacheRepository repository = new CacheRepository();
        String key = "overwriteKey";
        String firstValue = "firstValue";
        String secondValue = "secondValue";
        // WHEN: Overwriting the key with a new value
        repository.put(key, firstValue);
        repository.put(key, secondValue);
        Object result = repository.get(key);
        // THEN: The retrieved value should be the latest one
        assertEquals(secondValue, result);
    }

    @Test
    void testRemoveExistingKey() {
        // GIVEN: A CacheRepository instance with a stored key-value pair
        CacheRepository repository = new CacheRepository();
        String key = "removeKey";
        String value = "removeValue";
        repository.put(key, value);
        // WHEN: Removing the key
        repository.remove(key);
        Object result = repository.get(key);
        // THEN: The retrieved value should be null
        assertEquals(null, result);
    }

    @Test
    void testRemoveNonExistingKey() {
        // GIVEN: A CacheRepository instance and a non-existing key
        CacheRepository repository = new CacheRepository();
        String key = "nonExistingKey";
        // WHEN: Removing the key
        repository.remove(key);
        Object result = repository.get(key);
        // THEN: The retrieved value should be null
        assertEquals(null, result);
    }

    @Test
    void testClearCache() {
        // GIVEN: A CacheRepository instance with multiple entries
        CacheRepository repository = new CacheRepository();
        repository.put("key1", "value1");
        repository.put("key2", "value2");
        repository.put("key3", "value3");
        // WHEN: Clearing the cache
        repository.clear();
        Object result1 = repository.get("key1");
        Object result2 = repository.get("key2");
        Object result3 = repository.get("key3");
        // THEN: All retrieved values should be null
        assertEquals(null, result1);
        assertEquals(null, result2);
        assertEquals(null, result3);
    }

    @Test
    void testCacheSizeAfterOperations() {
        // GIVEN: A CacheRepository instance
        CacheRepository repository = new CacheRepository();
        repository.put("key1", "value1");
        repository.put("key2", "value2");
        repository.remove("key1");
        // WHEN: Checking the size
        int size = repository.size();
        // THEN: The size should reflect the remaining entries
        assertEquals(1, size);
    }

    @Test
    void testCacheSizeAfterClear() {
        // GIVEN: A CacheRepository instance with entries
        CacheRepository repository = new CacheRepository();
        repository.put("key1", "value1");
        repository.put("key2", "value2");
        // WHEN: Clearing the cache
        repository.clear();
        int size = repository.size();
        // THEN: The size should be zero
        assertEquals(0, size);
    }
}
