package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.MockitoExtension;

package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RedisCacheRepositoryGeneratedAiTests {

    private RedisCacheRepository redisCacheRepository;

    @BeforeEach
    void setUp() {
        redisCacheRepository = new RedisCacheRepository();
    }

    // GIVEN: A new RedisCacheRepository instance is created.
    // WHEN: The get method is called without any arguments.
    // THEN: The method should return a valid RedisCacheRepository instance.
    @Test
    void testGet_returnsValidInstance() {
        RedisCacheRepository result = redisCacheRepository.get();
        assertNotNull(result);
        assertInstanceOf(RedisCacheRepository.class, result);
    }

    // GIVEN: A key is provided to the get method.
    // WHEN: The get method is called with the specified key.
    // THEN: The method should return a valid RedisCacheRepository instance.
    @Test
    void testGet_withKey_returnsValidInstance() {
        String key = "testKey";
        RedisCacheRepository result = redisCacheRepository.get(key);
        assertNotNull(result);
        assertInstanceOf(RedisCacheRepository.class, result);
    }

    // GIVEN: A key is provided to the get method and a custom object is expected.
    // WHEN: The get method is called with the specified key and a custom object.
    // THEN: The method should return a valid RedisCacheRepository instance.
    @Test
    void testGet_withKeyAndCustomObject_returnsValidInstance() {
        String key = "testKey";
        RedisCacheRepository result = redisCacheRepository.get(key, "customObject");
        assertNotNull(result);
        assertInstanceOf(RedisCacheRepository.class, result);
    }
}
