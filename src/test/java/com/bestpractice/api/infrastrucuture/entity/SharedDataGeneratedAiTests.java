package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedDataInstance_whenGetCreatedAt_thenReturnNullInitially() {
        // GIVEN
        // A new instance of SharedData is created in the setup method.

        // WHEN
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertNull(createdAt, "Expected createdAt to be null initially");
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersist_thenCreatedAtIsSetToCurrentDate() {
        // GIVEN
        // A new instance of SharedData is created in the setup method.

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertEquals(new Date().getTime() / 1000, sharedData.getCreatedAt().getTime() / 1000, "Expected createdAt to be set to the current date");
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAt_thenCreatedAtIsUpdated() {
        // GIVEN
        Date customDate = new Date();

        // WHEN
        sharedData.setCreatedAt(customDate);

        // THEN
        assertEquals(customDate, sharedData.getCreatedAt(), "Expected createdAt to be updated to the custom date");
    }
}
