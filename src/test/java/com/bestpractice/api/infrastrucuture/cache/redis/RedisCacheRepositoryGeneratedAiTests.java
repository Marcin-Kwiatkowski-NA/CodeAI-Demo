package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisCacheRepositoryGeneratedAiTests {

    private RedisCacheRepository redisCacheRepository;

    @BeforeEach
    void setUp() {
        redisCacheRepository = new RedisCacheRepository();
    }

    @Test
    void givenNewInstance_whenCreated_thenNotNull() {
        // GIVEN
        // A new instance of RedisCacheRepository is created in setUp()

        // WHEN
        RedisCacheRepository instance = redisCacheRepository;

        // THEN
        assertThat(instance).isNotNull();
    }

    @Test
    void givenConstructor_whenInvoked_thenCreatesDistinctInstances() {
        // GIVEN
        // Two separate instances of RedisCacheRepository

        // WHEN
        RedisCacheRepository instance1 = new RedisCacheRepository();
        RedisCacheRepository instance2 = new RedisCacheRepository();

        // THEN
        assertThat(instance1).isNotSameAs(instance2);
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void givenInstance_whenToStringCalled_thenContainsClassName() {
        // GIVEN
        RedisCacheRepository instance = new RedisCacheRepository();

        // WHEN
        String result = instance.toString();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).contains("RedisCacheRepository");
    }

    @Test
    void givenSameInstance_whenEqualsCalled_thenReturnsTrue() {
        // GIVEN
        RedisCacheRepository instance = redisCacheRepository;

        // WHEN
        boolean result = instance.equals(instance);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void givenDifferentInstances_whenEqualsCalled_thenReturnsFalse() {
        // GIVEN
        RedisCacheRepository instance1 = new RedisCacheRepository();
        RedisCacheRepository instance2 = new RedisCacheRepository();

        // WHEN
        boolean result = instance1.equals(instance2);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void givenNull_whenEqualsCalled_thenReturnsFalse() {
        // GIVEN
        RedisCacheRepository instance = new RedisCacheRepository();

        // WHEN
        boolean result = instance.equals(null);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void givenDifferentType_whenEqualsCalled_thenReturnsFalse() {
        // GIVEN
        RedisCacheRepository instance = new RedisCacheRepository();
        Object otherType = new Object();

        // WHEN
        boolean result = instance.equals(otherType);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void givenInstance_whenHashCodeCalledMultipleTimes_thenConsistentResult() {
        // GIVEN
        RedisCacheRepository instance = new RedisCacheRepository();

        // WHEN
        int hash1 = instance.hashCode();
        int hash2 = instance.hashCode();

        // THEN
        assertEquals(hash1, hash2);
    }

    @Test
    void givenTwoDifferentInstances_whenHashCodesCompared_thenMayDifferButValid() {
        // GIVEN
        RedisCacheRepository instance1 = new RedisCacheRepository();
        RedisCacheRepository instance2 = new RedisCacheRepository();

        // WHEN
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();

        // THEN
        assertThat(hash1).isInstanceOf(Integer.class);
        assertThat(hash2).isInstanceOf(Integer.class);
    }

    @Test
    void givenInstance_whenToStringCalledMultipleTimes_thenConsistentResult() {
        // GIVEN
        RedisCacheRepository instance = new RedisCacheRepository();

        // WHEN
        String str1 = instance.toString();
        String str2 = instance.toString();

        // THEN
        assertThat(str1).isEqualTo(str2);
    }

    @Test
    void givenInstance_whenEqualsAndHashCodeConsistencyChecked_thenConsistentBehavior() {
        // GIVEN
        RedisCacheRepository instance1 = new RedisCacheRepository();
        RedisCacheRepository instance2 = instance1;

        // WHEN
        boolean equalsResult = instance1.equals(instance2);
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();

        // THEN
        assertThat(equalsResult).isTrue();
        assertEquals(hash1, hash2);
    }

    @Test
    void givenInstance_whenToStringCalled_thenStartsWithClassName() {
        // GIVEN
        RedisCacheRepository instance = new RedisCacheRepository();

        // WHEN
        String result = instance.toString();

        // THEN
        assertThat(result).startsWith("com.bestpractice.api.infrastrucuture.cache.redis.RedisCacheRepository");
    }
}
