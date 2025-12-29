package com.bestpractice.api.infrastrucuture.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.bestpractice.api.infrastrucuture.cache.CacheRepository;

import org.junit.jupiter.api.Test;

class CacheRepositoryGeneratedAiTests {

    @Test
    void shouldReturnEmptyStringWhenNoDataIsPresent() {
        // GIVEN
        String expected = "";
        // WHEN
        String result = new CacheRepository().get("key");
        // THEN
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldReturnNullWhenKeyIsMissing() {
        // GIVEN
        String key = "missing-key";
        // WHEN
        String result = new CacheRepository().get(key);
        // THEN
        assertThat(result).isNull();
    }

    @Test
    void shouldStoreAndRetrieveDataSuccessfully() {
        // GIVEN
        String key = "test-key";
        String value = "test-value";
        // WHEN
        new CacheRepository().put(key, value);
        String result = new CacheRepository().get(key);
        // THEN
        assertThat(result).isEqualTo(value);
    }

    @Test
    void shouldThrowExceptionWhenInvalidOperationIsPerformed() {
        // GIVEN
        String key = "invalid-key";
        // WHEN & THEN
        assertThatThrownBy(() -> new CacheRepository().remove(key))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("Operation not supported");
    }
}
