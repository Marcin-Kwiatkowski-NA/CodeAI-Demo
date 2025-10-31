package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date();

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
        // GIVEN: a SharedData instance with an existing createdAt
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a newer date
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a SharedData instance

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(null);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: calling onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistReplacesNullCreatedAt() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEvenIfAlreadySet() {
        // GIVEN: createdAt is set to a specific date
        Date initialDate = new Date(System.currentTimeMillis() - 5000);
        sharedData.setCreatedAt(initialDate);

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a newer date
        assertThat(sharedData.getCreatedAt()).isAfter(initialDate);
    }

    @Test
    void testSetCreatedAtDoesNotThrowForValidDate() {
        // GIVEN: a valid date
        Date validDate = new Date();

        // WHEN & THEN: setting createdAt should not throw any exception
        assertDoesNotThrow(() -> sharedData.setCreatedAt(validDate));
    }
}
