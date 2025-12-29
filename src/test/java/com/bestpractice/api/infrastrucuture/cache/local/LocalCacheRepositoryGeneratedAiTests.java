package com.bestpractice.api.infrastrucuture.cache.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bestpractice.api.infrastrucuture.cache.local.LocalCacheRepository;

import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
public class LocalCacheRepositoryGeneratedAiTests {

    @BeforeEach
    public void setUp() {
        // Reset any state or mock behavior before each test
    }

    @Test
    public void givenValidKey_whenGetCacheValue_thenReturnsStoredValue() {
        // GIVEN
        String key = "testKey";
        Object value = "testValue";
        LocalCacheRepository repository = new LocalCacheRepository();
        repository.put(key, value);

        // WHEN
        Object result = repository.get(key);

        // THEN
        assertThat(result).isEqualTo(value);
    }

    @Test
    public void givenNonExistentKey_whenGetCacheValue_thenReturnsNull() {
        // GIVEN
        String key = "nonExistentKey";

        // WHEN
        Object result = new LocalCacheRepository().get(key);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    public void givenValidKey_whenPutValue_thenCacheStoresValue() {
        // GIVEN
        String key = "putKey";
        Object value = "putValue";

        // WHEN
        LocalCacheRepository repository = new LocalCacheRepository();
        repository.put(key, value);

        // THEN
        assertThat(repository.get(key)).isEqualTo(value);
    }

    @Test
    public void givenLargeKey_whenPutValue_thenDoesNotThrowException() {
        // GIVEN
        String longKey = "a".repeat(1000);
        Object value = "largeValue";

        // WHEN
        LocalCacheRepository repository = new LocalCacheRepository();
        repository.put(longKey, value);

        // THEN
        assertThat(repository.get(longKey)).isEqualTo(value);
    }

    @Test
    public void givenNullKey_whenPutValue_thenThrowsIllegalArgumentException() {
        // GIVEN
        String key = null;

        // WHEN & THEN
        assertThatThrownBy(() -> new LocalCacheRepository().put(key, "anyValue"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Key cannot be null");
    }

    @Test
    public void givenNullValue_whenPutValue_thenThrowsIllegalArgumentException() {
        // GIVEN
        String key = "keyWithNullValue";

        // WHEN & THEN
        assertThatThrownBy(() -> new LocalCacheRepository().put(key, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Value cannot be null");
    }
}
