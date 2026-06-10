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

    private CacheRepository cacheRepository;

    @BeforeEach
    void setUp() {
        // GIVEN: Initialize a new instance before each test
        cacheRepository = new CacheRepository();
    }

    @Test
    void testPutAndGetWithValidKeyAndValue() {
        // GIVEN: A valid key and value
        String key = "testKey";
        String value = "testValue";
        // WHEN: Putting and then getting the value
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);
        // THEN: The retrieved value should match the stored one
        assertEquals(value, result);
    }

    @Test
    void testGetWithNonExistingKey() {
        // GIVEN: A key that does not exist
        String key = "nonExistingKey";
        // WHEN: Getting the value
        Object result = cacheRepository.get(key);
        // THEN: The result should be null
        assertEquals(null, result);
    }

    @Test
    void testPutWithNullKey() {
        // GIVEN: A null key and a valid value
        String key = null;
        String value = "value";
        // WHEN & THEN: Expect NullPointerException
        assertThrows(NullPointerException.class, () -> cacheRepository.put(key, value));
    }

    @Test
    void testPutWithNullValue() {
        // GIVEN: A valid key and null value
        String key = "key";
        String value = null;
        // WHEN: Putting null value
        cacheRepository.put(key, value);
        // THEN: Getting the key should return null
        Object result = cacheRepository.get(key);
        assertEquals(null, result);
    }

    @Test
    void testRemoveExistingKey() {
        // GIVEN: A key-value pair in cache
        String key = "keyToRemove";
        String value = "value";
        cacheRepository.put(key, value);
        // WHEN: Removing the key
        cacheRepository.remove(key);
        // THEN: The key should no longer exist
        Object result = cacheRepository.get(key);
        assertEquals(null, result);
    }

    @Test
    void testRemoveNonExistingKey() {
        // GIVEN: A key that does not exist
        String key = "nonExistingKey";
        // WHEN: Removing the key
        cacheRepository.remove(key);
        // THEN: No exception should be thrown and cache remains consistent
        assertThat(cacheRepository.get(key)).isNull();
    }

    @Test
    void testClearCache() {
        // GIVEN: Multiple entries in cache
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");
        // WHEN: Clearing the cache
        cacheRepository.clear();
        // THEN: All entries should be removed
        assertThat(cacheRepository.get("key1")).isNull();
        assertThat(cacheRepository.get("key2")).isNull();
    }

    @Test
    void testPutAndGetWithEmptyStringKey() {
        // GIVEN: An empty string key
        String key = "";
        String value = "emptyKeyValue";
        // WHEN: Putting and getting the value
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);
        // THEN: The retrieved value should match the stored one
        assertEquals(value, result);
    }

    @Test
    void testPutAndGetWithWhitespaceKey() {
        // GIVEN: A whitespace key
        String key = "   ";
        String value = "whitespaceValue";
        // WHEN: Putting and getting the value
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);
        // THEN: The retrieved value should match the stored one
        assertEquals(value, result);
    }

    @Test
    void testPutAndGetWithUnicodeKey() {
        // GIVEN: A Unicode key
        String key = "ключ";
        String value = "значение";
        // WHEN: Putting and getting the value
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);
        // THEN: The retrieved value should match the stored one
        assertEquals(value, result);
    }

    @Test
    void testPutAndGetWithSpecialCharactersKey() {
        // GIVEN: A key with special characters
        String key = "!@#$%^&*()";
        String value = "specialValue";
        // WHEN: Putting and getting the value
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);
        // THEN: The retrieved value should match the stored one
        assertEquals(value, result);
    }

    @Test
    void testOverwriteExistingKey() {
        // GIVEN: A key with an initial value
        String key = "overwriteKey";
        cacheRepository.put(key, "initialValue");
        // WHEN: Overwriting with a new value
        cacheRepository.put(key, "newValue");
        Object result = cacheRepository.get(key);
        // THEN: The new value should replace the old one
        assertEquals("newValue", result);
    }

    @Test
    void testCacheSizeAfterMultiplePutsAndRemoves() {
        // GIVEN: Multiple entries added and removed
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");
        cacheRepository.remove("key1");
        // WHEN: Checking remaining key
        Object result = cacheRepository.get("key2");
        // THEN: Only key2 should remain
        assertEquals("value2", result);
    }
}
