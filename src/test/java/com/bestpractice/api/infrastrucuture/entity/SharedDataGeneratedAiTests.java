package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenNewSharedData_whenSetCreatedAt_thenValueIsStoredCorrectly() {
        // GIVEN
        Date now = new Date();

        // WHEN
        sharedData.setCreatedAt(now);

        // THEN
        assertEquals(now, sharedData.getCreatedAt(), "The createdAt value should match the one set.");
    }

    @Test
    void givenSharedData_whenOnPrePersistCalled_thenCreatedAtIsSet() {
        // GIVEN
        assertNull(sharedData.getCreatedAt(), "Initially, createdAt should be null.");

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt(), "createdAt should be set after onPrePersist is called.");
        assertTrue(sharedData.getCreatedAt() instanceof Date, "createdAt should be an instance of Date.");
    }

    @Test
    void givenSharedDataWithExistingCreatedAt_whenOnPrePersistCalled_thenCreatedAtIsOverwritten() {
        // GIVEN
        Date oldDate = new Date(System.currentTimeMillis() - 10000);
        sharedData.setCreatedAt(oldDate);

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt(), "createdAt should not be null after onPrePersist.");
        assertTrue(sharedData.getCreatedAt().after(oldDate), "createdAt should be updated to a newer timestamp.");
    }

    @Test
    void givenSharedData_whenSetCreatedAtWithNull_thenCreatedAtIsNull() {
        // GIVEN
        Date date = null;

        // WHEN
        sharedData.setCreatedAt(date);

        // THEN
        assertNull(sharedData.getCreatedAt(), "createdAt should remain null when set with null.");
    }

    @Test
    void givenSharedData_whenGetCreatedAtWithoutSetting_thenReturnsNull() {
        // GIVEN
        // No createdAt set

        // WHEN
        Date result = sharedData.getCreatedAt();

        // THEN
        assertNull(result, "createdAt should be null if not explicitly set or persisted.");
    }
}
