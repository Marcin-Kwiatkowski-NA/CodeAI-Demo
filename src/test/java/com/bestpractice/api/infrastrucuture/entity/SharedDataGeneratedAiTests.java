package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenNewSharedData_whenSetCreatedAt_thenCreatedAtIsSetCorrectly() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        sharedData.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedDate, sharedData.getCreatedAt());
    }

    @Test
    void givenSharedData_whenOnPrePersist_thenCreatedAtIsAutomaticallySet() {
        // GIVEN
        assertNull(sharedData.getCreatedAt());

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt() instanceof Date);
    }

    @Test
    void givenSharedDataWithExistingCreatedAt_whenOnPrePersist_thenCreatedAtIsUpdated() {
        // GIVEN
        Date oldDate = new Date(System.currentTimeMillis() - 1000);
        sharedData.setCreatedAt(oldDate);

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void givenSharedData_whenSetCreatedAtWithNull_thenCreatedAtIsNull() {
        // GIVEN
        Date nullDate = null;

        // WHEN
        sharedData.setCreatedAt(nullDate);

        // THEN
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void givenSharedData_whenSetCreatedAtWithInvalidType_thenThrowsClassCastException() {
        // GIVEN
        Object invalidDate = "not a date";

        // WHEN / THEN
        assertThrows(ClassCastException.class, () -> {
            // Simulate incorrect usage by casting manually
            sharedData.setCreatedAt((Date) invalidDate);
        });
    }
}
