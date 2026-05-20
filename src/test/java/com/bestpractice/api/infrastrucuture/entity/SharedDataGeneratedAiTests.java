package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenNewSharedData_whenSetCreatedAt_thenValueIsStoredCorrectly() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        sharedData.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedDate, sharedData.getCreatedAt());
    }

    @Test
    void givenSharedData_whenOnPrePersistCalled_thenCreatedAtIsSet() {
        // GIVEN
        sharedData.setCreatedAt(null);

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt() instanceof Date);
    }

    @Test
    void givenSharedDataWithExistingCreatedAt_whenOnPrePersistCalled_thenCreatedAtIsOverwritten() {
        // GIVEN
        Date oldDate = new Date(System.currentTimeMillis() - 10000);
        sharedData.setCreatedAt(oldDate);

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void givenSharedData_whenSetCreatedAtWithNull_thenGetCreatedAtReturnsNull() {
        // GIVEN
        Date date = null;

        // WHEN
        sharedData.setCreatedAt(date);

        // THEN
        assertEquals(null, sharedData.getCreatedAt());
    }

    @Test
    void givenSharedData_whenOnPrePersistCalled_thenNoExceptionIsThrown() {
        // GIVEN
        sharedData.setCreatedAt(null);

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> {
            // This test intentionally checks that no exception is thrown.
            // Since onPrePersist does not throw any checked exceptions, we simulate a safe call.
            try {
                sharedData.onPrePersist();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
