package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersistCalled_thenCreatedAtIsSet() {
        // GIVEN
        assertNull(sharedData.getCreatedAt(), "createdAt should initially be null");

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt(), "createdAt should be set after onPrePersist");
        assertEquals(true, sharedData.getCreatedAt().before(new Date()) || sharedData.getCreatedAt().equals(new Date()), "createdAt should be before or equal to the current date");
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAtCalled_thenCreatedAtIsUpdated() {
        // GIVEN
        Date newDate = new Date();

        // WHEN
        sharedData.setCreatedAt(newDate);

        // THEN
        assertEquals(newDate, sharedData.getCreatedAt(), "createdAt should be updated to the new date");
    }

    @Test
    void givenSharedDataInstance_whenGetCreatedAtCalled_thenReturnsCorrectValue() {
        // GIVEN
        Date newDate = new Date();
        sharedData.setCreatedAt(newDate);

        // WHEN
        Date result = sharedData.getCreatedAt();

        // THEN
        assertEquals(newDate, result, "getCreatedAt should return the correct date");
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAtCalledWithNull_thenThrowsException() {
        // GIVEN
        Date nullDate = null;

        // WHEN & THEN
        try {
            sharedData.setCreatedAt(nullDate);
        } catch (IllegalArgumentException e) {
            assertEquals("createdAt cannot be null", e.getMessage(), "Exception message should match");
        }
    }
}
