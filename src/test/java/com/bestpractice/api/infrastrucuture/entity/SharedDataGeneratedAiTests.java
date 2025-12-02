package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedDataInstance_whenGetCreatedAtCalled_thenReturnsNullInitially() {
        // GIVEN
        // A new instance of SharedData is created in the setup.

        // WHEN
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertNull(createdAt);
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersistCalled_thenCreatedAtIsSet() {
        // GIVEN
        // A new instance of SharedData is created in the setup.

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt());
        assertEquals(new Date().getTime() / 1000, sharedData.getCreatedAt().getTime() / 1000, 1);
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAtCalled_thenCreatedAtIsUpdated() {
        // GIVEN
        Date newDate = new Date();

        // WHEN
        sharedData.setCreatedAt(newDate);

        // THEN
        assertEquals(newDate, sharedData.getCreatedAt());
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAtCalledWithNull_thenCreatedAtIsUpdatedToNull() {
        // GIVEN
        // A new instance of SharedData is created in the setup.

        // WHEN
        sharedData.setCreatedAt(null);

        // THEN
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersistCalledMultipleTimes_thenCreatedAtIsUpdatedEachTime() {
        // GIVEN
        // A new instance of SharedData is created in the setup.

        // WHEN
        sharedData.onPrePersist();
        Date firstPersistDate = sharedData.getCreatedAt();

        sharedData.onPrePersist();
        Date secondPersistDate = sharedData.getCreatedAt();

        // THEN
        assertNotNull(firstPersistDate);
        assertNotNull(secondPersistDate);
        assertEquals(secondPersistDate.getTime() / 1000, new Date().getTime() / 1000, 1);
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAtCalledWithFutureDate_thenCreatedAtIsUpdated() {
        // GIVEN
        Date futureDate = new Date(System.currentTimeMillis() + 100000);

        // WHEN
        sharedData.setCreatedAt(futureDate);

        // THEN
        assertEquals(futureDate, sharedData.getCreatedAt());
    }
}
