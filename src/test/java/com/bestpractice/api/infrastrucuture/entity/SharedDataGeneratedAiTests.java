package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedDataInstance_whenGetCreatedAt_thenReturnNullInitially() {
        // GIVEN
        // A new instance of SharedData is created in the setup.

        // WHEN
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertNull(createdAt, "Expected createdAt to be null initially");
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAt_thenCreatedAtIsUpdated() {
        // GIVEN
        Date now = new Date();

        // WHEN
        sharedData.setCreatedAt(now);

        // THEN
        assertEquals(now, sharedData.getCreatedAt(), "Expected createdAt to be updated to the provided date");
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersist_thenCreatedAtIsSetToCurrentDate() {
        // GIVEN
        // A new instance of SharedData is created in the setup.

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt(), "Expected createdAt to be set to a non-null value");
        long difference = Math.abs(sharedData.getCreatedAt().getTime() - new Date().getTime());
        assertEquals(true, difference < 1000, "Expected createdAt to be close to the current date");
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAtWithNull_thenHandleGracefully() {
        // GIVEN
        // A new instance of SharedData is created in the setup.

        // WHEN
        sharedData.setCreatedAt(null);

        // THEN
        assertNull(sharedData.getCreatedAt(), "Expected createdAt to remain null when set to null");
    }
}
